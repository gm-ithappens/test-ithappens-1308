package com.springsql.negocio.controller;

import com.springsql.negocio.dao.*;
import com.springsql.negocio.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@RestController
public class Controller {

    @Autowired
    private ProdutoDao produtoDao;
    @Autowired
    private ClienteDao clienteDao;
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private FilialDao filialDao;
    @Autowired
    private FormaPagamentoDao formaPagamentoDao;
    @Autowired
    private PedidoDao pedidoDao;
    @Autowired
    private ItensPedidoDao itensPedidoDao;
    @Autowired
    private EstoqueDao estoqueDao;

    /////ADD USUARIO

    @PostMapping("/addusuarios")
    public String addusuarios(@RequestBody List<Usuario> user) {
        usuarioDao.saveAll(user);
        return "Usuários adicionados:" + user.size();

    }
    @PostMapping("/getusuarios")
    public List<Usuario> getusuarios() {
        return (List<Usuario>)  usuarioDao.findAll();
    }
////// ----

    ////add Produtos e get

    @PostMapping("/addprodutos")
    public String addProduto(@RequestBody List<Produto> produtos) {
        produtoDao.saveAll(produtos);
        return "Produtos adicionados:" + produtos.size();

    }
    @PostMapping("/getprodutos")
    public List<Produto> getProduto() {
        return (List<Produto>)  produtoDao.findAll();
    }
    @PostMapping("/getprodutoid")
    public Produto getProdutoid(@RequestBody int id) {
        List<Produto> lista = (List<Produto>)  produtoDao.findAll();
        for(Produto prod : lista)
        {
            if(prod.getId()==id){
                return prod;
            }
        }
        return null;
    }

    @PostMapping("/getprodutocod")
    public Produto getProdutocod(@RequestBody String cod) {
        List<Produto> lista = (List<Produto>)  produtoDao.findAll();
        for(Produto prod : lista)
        {
            if(prod.getCodbarra().equals(cod)){
                return prod;
            }
        }
        return null;
    }
    @PostMapping("/getprodutodesc")
    public Produto getProdutodesc(@RequestBody String desc) {
        List<Produto> lista = (List<Produto>)  produtoDao.findAll();
        for(Produto prod : lista)
        {
            if(prod.getDescricao().equals(desc)){
                return prod;
            }
        }
        return null;
    }

    //// ---////

    /// novo pedido e get
    @PostMapping("/novopedido")
    public String novopedido(@RequestBody PedidoEstoque pedido) {

        pedidoDao.save(pedido);
        return "Pedido Criado : " + pedido;
    }
    @PostMapping("/getpedidos")
    public List<PedidoEstoque> getpedido() {
        return (List<PedidoEstoque>)  pedidoDao.findAll();
    }

    @PostMapping("/getpedidoid")
    public PedidoEstoque getpedidoid(@RequestBody int id) {
        List<PedidoEstoque> lista = (List<PedidoEstoque>)  pedidoDao.findAll();
        for(PedidoEstoque prod : lista)
        {
            if(prod.getId()== id){
                return prod;
            }
        }
        return null;
    }
    @PostMapping("/fecharpedido")
    public String fecharpedido(@RequestBody int idpedido ) {
        PedidoEstoque pedido =  pedidoDao.findById(idpedido).get();
        List<ItensPedido> itensdopedido = itensdopedido(idpedido);
        List<Estoque> estoquetotal= (List<Estoque>) estoqueDao.findAll();
        boolean temestoque = false;

        for(ItensPedido item : itensdopedido)
        {
            if(item.getStatus().equals("ativo")) {

                for (Estoque estoque : estoquetotal) {
                    if (estoque.getCodFilial() == pedido.getCodFilial() && estoque.getCodProduto() == item.getCodProduto()) {
                        if (pedido.getTipo().equals("saida")) {
                            if (estoque.getQuantidade() >= item.getQuantidade()) {
                                estoque.setQuantidade(estoque.getQuantidade() - item.getQuantidade());
                            } else {
                                return "Não há estoque disponivel";
                            }
                        } else {
                            estoque.setQuantidade(estoque.getQuantidade() + item.getQuantidade());

                        }
                        temestoque = true;
                    }
                }
                if(!temestoque) {
                    if (pedido.getTipo().equals("entrada")) {
                        Estoque novo = new Estoque();
                        novo.setCodFilial(pedido.getCodFilial());
                        novo.setCodProduto(item.getCodProduto());
                        novo.setQuantidade(item.getQuantidade());
                        estoqueDao.save(novo);
                    }else{
                        return "Não há estoque disponivel";
                    }
                }
                item.setStatus("Processado");
                itensPedidoDao.save(item);
            }

        }
        return "Pedido Finalizado!";

    }


    ///////////////////-------------///////////////

    /////ITENS DO PEDIDO

    ///adicionar itens ao pedido
    @PostMapping("/itempedido")
    public String itenspedido(@RequestBody ItensPedido item) {
        PedidoEstoque pedido = pedidoDao.findById(item.getCodPedido()).get();
        Produto produto = produtoDao.findById(item.getCodProduto()).get();
        List<ItensPedido> lista = (List<ItensPedido>)  itensPedidoDao.findAll();
        
                for (ItensPedido itempedido : lista) {
                    if (item.getCodPedido() == itempedido.getCodPedido() && item.getCodProduto() == itempedido.getCodProduto() && itempedido.getStatus().contentEquals("ativo")) {

                            item.setValor(produto.getValor());
                            itempedido.setQuantidade(itempedido.getQuantidade() + item.getQuantidade());
                            itempedido.setTotal(item.getValor() * itempedido.getQuantidade());
                            itensPedidoDao.save(itempedido);
                            pedido.setItens(pedido.getItens() + item.getQuantidade());
                            pedido.setValor(pedido.getValor() + (item.getValor()*item.getQuantidade()));
                            pedidoDao.save(pedido);
                            return "Item adicionado: " + item;

                    }
                }

                    item.setValor(produto.getValor());
                    item.setTotal(item.getQuantidade() * item.getValor());
                    item.setStatus("ativo");
                    itensPedidoDao.save(item);
                    pedido.setItens(pedido.getItens() + item.getQuantidade());
                    pedido.setValor(pedido.getValor() + item.getTotal());
                    pedidoDao.save(pedido);
                    return "Item adicionado: " + item;



    }

    /// itens do pedido pelo id
    @PostMapping("/listaritensid")
    public List<ItensPedido> itensdopedido(@RequestBody int idpedido){
        List<ItensPedido> lista = (List<ItensPedido>)  itensPedidoDao.findAll();
        List<ItensPedido> retorno = new ArrayList<ItensPedido>();
        for(ItensPedido item : lista)
        {
            if(item.getCodPedido()==idpedido){

                retorno.add(item)   ;
            }

        }

        return retorno;
    }

    ///CANCELAR ITENS
    @PostMapping("/cancelaritens")
    public String cancelaritens(@RequestBody Cancelamento cancelamento){
        PedidoEstoque pedido = pedidoDao.findById(cancelamento.getIdpedido()).get();
        List<ItensPedido> lista = (List<ItensPedido>)  itensPedidoDao.findAll();

        for(ItensPedido item : lista)
        {
            if(item.getCodPedido()==cancelamento.getIdpedido() && item.getCodProduto()==cancelamento.getIdproduto() && item.getStatus().equals("ativo")){
                item.setStatus("cancelado");
                itensPedidoDao.save(item);
                pedido.setItens(pedido.getItens()- item.getQuantidade());
                pedido.setValor(pedido.getValor()-item.getTotal());
                pedidoDao.save(pedido);
                return "Item cancelado: "+ item;
            }

        }
        return "nenhum item cancelado";
    }

//////------------///////////////////
///// ADD CLIENTES
    @PostMapping("/addclientes")
    public String addclientes(@RequestBody List<Cliente> clientes) {
        clienteDao.saveAll(clientes);
        return "Clientes adicionados:" + clientes.size();

    }
    @PostMapping("/getclientes")
    public List<Cliente> getcliente() {
        return (List<Cliente>)  clienteDao.findAll();
    }

///////-
    ////// ADD ESTOQUE
    @PostMapping("/addestoque")
    public String addestoque(@RequestBody List<Estoque> estoque) {
        estoqueDao.saveAll(estoque);
        return "Estoque adicionado:" + estoque.size();

    }
    @PostMapping("/getestoque")
    public List<Estoque> getestoque() {
        return (List<Estoque>)  estoqueDao.findAll();
    }

//////---
    ////// ADD FILIAL
@PostMapping("/addfilial")
public String addfilial(@RequestBody List<Filial> filials) {
    filialDao.saveAll(filials);
    return "Filiais adicionado:" + filials.size();

}
    @PostMapping("/getfilial")
    public List<Filial> getfilial() {
        return (List<Filial>)  filialDao.findAll();
    }
    ////----
    /////// ADD FORMA PAGAMENTO
    @PostMapping("/addpagamento")
    public String addpagamento(@RequestBody List<FormaPagamento> pag) {
        formaPagamentoDao.saveAll(pag);
        return "Formas de pagamento:" + pag.size();

    }
    @PostMapping("/getpagamento")
    public List<FormaPagamento> getpagamento() {
        return (List<FormaPagamento>)  formaPagamentoDao.findAll();
    }
///////////---

}
