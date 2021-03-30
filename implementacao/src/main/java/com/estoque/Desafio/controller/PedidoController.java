package com.estoque.Desafio.controller;

import com.estoque.Desafio.models.*;
import com.estoque.Desafio.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class PedidoController {

    private List<PedidoItem> listaPedido = new ArrayList<PedidoItem>();
    private List<String> messages = new ArrayList<String>();

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoItemRepository pedidoItemRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private FilialRepository filialRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    private List<String> listaMessages = new ArrayList<>();

    @GetMapping(path="/pedidos/{filial}/cadastrar")
    public ModelAndView cadastrar(@PathVariable("filial") Integer filial_id, Pedido pedido,
                                  PedidoItem pedidoItem){
        ModelAndView mv = new ModelAndView("pedidos/cadastro");
        Optional<Filial> filial = filialRepository.findById(filial_id);
        //Repassar objetos para a View
        System.out.println("Messages");
        System.out.println(this.listaMessages);
        mv.addObject("messages",this.listaMessages);
        mv.addObject("pedido", pedido);
        mv.addObject("listaPedidoItens", this.listaPedido);
        mv.addObject("pedidoItem", pedidoItem);
        mv.addObject("usuarios", usuarioRepository.findByFilial(filial_id));
        mv.addObject("produtos", estoqueRepository.findByFilial(filial.get().getId()));
        mv.addObject("clientes", clienteRepository.findAll());
        mv.addObject("formasPagamentos", formaPagamentoRepository.findAll());
        mv.addObject("filial", filial.get());
        return mv;
    }

    @GetMapping(path="/pedidos/{filial}/listar")
    public ModelAndView listar(@PathVariable("filial") Integer filial_id){
        ModelAndView mv = new ModelAndView("pedidos/lista");
        mv.addObject("itens", pedidoItemRepository.findByFilial(filial_id));
        mv.addObject("filial_id", filial_id);
        return mv;
    }

    @PostMapping(path="/pedidos/{filial}/salvar")
    public ModelAndView salvar(String acao,@PathVariable("filial") Integer filial_id, Pedido pedido,
                               PedidoItem pedidoItem){
        this.listaMessages = new ArrayList<String>();
        if(acao.equals("itens")){
            // Se o Botão de name acao enviar a string "itens", então apenas adicionar os itens à Lista
            //Inicializar status do item com Ativo
            pedidoItem.setStatus(pedidoItem.ATIVO);

            //usuário deve inserir quantidade maior que zero
            if(pedidoItem.getQuantidade() != null && pedidoItem.getQuantidade() > 0){

                if(pedidoItem.getValor() == null){
                    this.listaMessages.add("Valor unitário deve ser informado");
                }else{
                    pedidoItem.setTotal(pedidoItem.getQuantidade()*pedidoItem.getValor());
                }

                //quantidade informada deve ser menor que o disponível no estoque se o tipo for Saída
                Optional<Estoque> produtoEstoque = estoqueRepository.findEstoqueByFilialAndProduto(pedido.getFilial().getId(),pedidoItem.getProduto().getId());
                if (pedidoItem.getQuantidade() < produtoEstoque.get().getQtde() || !pedido.isSaida()){
                    this.listaPedido.add(pedidoItem);
                }else{
                    this.listaMessages.add("Quantidade de "+pedidoItem.getProduto().getDescricao()+" é superior à quantidade disponível em estoque: "+produtoEstoque.get().getQtde());
                }
            }else{
                this.listaMessages.add("Quantidade deve ser superior a 0");
            }


        }else if(acao.equals("salvar")){
            this.messages = new ArrayList<String>();

            // Se o Botão de name acao enviar a string "salvar", então realizar este bloco
            pedidoRepository.saveAndFlush(pedido);

            //percorrer todos os itens e relacionar à Entrada
            double total = 0;
            for(PedidoItem item:listaPedido){

                item.setPedido(pedido);
                total += item.getTotal();
                //recuperar o estoque relacionado à filial e produto
                Optional<Estoque> est = estoqueRepository.findEstoqueByFilialAndProduto(item.getPedido().getFilial().getId(),item.getProduto().getId());
                //atualizar a quantidade no estoque
                Estoque estoque = est.get();
                //produto deve ter estoque positivo e se maior ou igual a qtde de saída
                if (pedido.isSaida()){
                    //decrementa
                    if (estoque.getQtde() > 0 && estoque.getQtde() >= item.getQuantidade()){
                        estoque.setQtde(estoque.getQtde() - item.getQuantidade());
                        item.setStatus(pedidoItem.PROCESSADO);
                        pedidoItemRepository.saveAndFlush(item);
                        estoqueRepository.saveAndFlush(estoque);
                    }else{
                        //não decrementar
                        this.messages.add("Produto "+item.getProduto().getDescricao()+" com estoque de "+est.get().getQtde());
                    }
                }else{
                    //incrementar
                    estoque.setQtde(estoque.getQtde() + item.getQuantidade());
                    item.setStatus(pedidoItem.PROCESSADO);
                    pedidoItemRepository.saveAndFlush(item);
                    estoqueRepository.saveAndFlush(estoque);
                }

            }
            pedido.setTotalPedido(total);
            pedidoRepository.saveAndFlush(pedido);

            //limpar o Array
            this.listaPedido = new ArrayList<PedidoItem>();
            return cadastrar(filial_id, new Pedido(), new PedidoItem());
        }
        return cadastrar(filial_id, pedido, new PedidoItem());
    }

}
