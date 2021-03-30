package com.estoque.Desafio.controller;

import com.estoque.Desafio.models.Produto;
import com.estoque.Desafio.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping(path="/produtos/cadastrar")
    public ModelAndView cadastrar(Produto produto){
        ModelAndView mv = new ModelAndView("produtos/cadastro");
        mv.addObject("produto", produto);
        return mv;
    }


    @GetMapping(path="/produtos/listar")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("produtos/lista");
        mv.addObject("produtos", produtoRepository.findAll());
        return mv;
    }


    @GetMapping(path="/produtos/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id){
        Optional<Produto> produto = produtoRepository.findById(id);
        return cadastrar(produto.get());
    }


    @GetMapping(path="/produtos/remover/{id}")
    public ModelAndView remover(@PathVariable("id") Integer id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        produtoRepository.delete(produto.get());
        return listar();
    }


    @PostMapping(path="/produtos/salvar")
    public ModelAndView salvar(Produto produto, BindingResult result){
        if (result.hasErrors()){
            return cadastrar(produto);
        }
        produtoRepository.saveAndFlush(produto);
        return cadastrar(new Produto());
    }


}
