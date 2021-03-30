package com.estoque.Desafio.seed;

import com.estoque.Desafio.models.*;
import com.estoque.Desafio.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class Seeder implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FilialRepository filialRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Override
    public void run(String... args) throws Exception {
        seedFiliais();
        seedUsuarios();
        seedProdutos();
        seedEstoques();
        seedClientes();
        seedFormaPagamentos();
    }

    private void seedFiliais() {
        if (filialRepository.count() == 0){
            //Criar Filiais ==========================================
            filialRepository.saveAndFlush(new Filial("Filial 1"));
            filialRepository.saveAndFlush(new Filial("Filial 2"));
            filialRepository.saveAndFlush(new Filial("Filial 3"));
        }
    }

    private void seedUsuarios() {
        List<Filial> filiais = filialRepository.findAll();
        if (usuarioRepository.count() == 0){
            // Criar Usuários ========================================
            for(Filial f:filiais){ // Para cada filial, criar um usuário
                usuarioRepository.saveAndFlush(
                        new Usuario("Usuário "+f.getId(),"user"+f.getId(),f)
                );
            }
        }
    }

    private void seedProdutos() {
        if (produtoRepository.count() == 0){
            //criar Produtos =========================================
            produtoRepository.saveAndFlush(new Produto("Arroz","1001"));
            produtoRepository.saveAndFlush(new Produto("Café","1002"));
            produtoRepository.saveAndFlush(new Produto("Leite","1003"));
            produtoRepository.saveAndFlush(new Produto("Açucar","1004"));
        }
    }

    private void seedEstoques() {
        List<Filial> filiais = filialRepository.findAll();
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<Produto> produtos = produtoRepository.findAll();
        Random random = new Random();  // Only one instance needed.

        if (estoqueRepository.count() == 0){
            filiais = filialRepository.findAll();
            for(Filial f:filiais){ // Para cada filial, criar um usuário
                for (Produto p:produtos){
                    estoqueRepository.saveAndFlush(new Estoque(p,f,(random.nextInt(100) / 100.0)*10));
                }
            }
        }
    }

    private void seedClientes() {
        if (clienteRepository.count() == 0){
            //criar Clientes =========================================
            for(int x = 1; x < 9; x++){

                clienteRepository.saveAndFlush(
                        new Cliente(
                                "Cliente "+x,//nome
                                String.valueOf(x).repeat(11), //cpf
                                String.valueOf(x).repeat(9), //celular
                                String.valueOf(x).repeat(8), //cep
                                "Endereço "+x, //endereco
                                "Bairro "+x, //bairro
                                "Cidade "+x) //cidade
                );
            }
        }
    }

    private void seedFormaPagamentos() {
        if (formaPagamentoRepository.count() == 0){
            //criar formas de Pagamento ==============================
            formaPagamentoRepository.saveAndFlush(new FormaPagamento("À Vista"));
            formaPagamentoRepository.saveAndFlush(new FormaPagamento("Boleto"));
            formaPagamentoRepository.saveAndFlush(new FormaPagamento("Cartão"));
        }
    }
}
