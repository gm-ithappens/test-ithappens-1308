package com.estoque.Desafio.controller;

import com.estoque.Desafio.models.Estoque;
import com.estoque.Desafio.repository.EstoqueRepository;
import com.estoque.Desafio.repository.FilialRepository;
import com.estoque.Desafio.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class EstoqueController {

    @Autowired
    private EstoqueRepository estoqueRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private FilialRepository filialRepository;


    @GetMapping(path="/estoques/cadastrar")
    public ModelAndView cadastrar(Estoque estoque){
        ModelAndView mv = new ModelAndView("estoques/cadastro");
        mv.addObject("estoque", estoque);
        mv.addObject("filiais", filialRepository.findAll());
        mv.addObject("produtos",produtoRepository.findAll());
        return mv;
    }


    @GetMapping(path="/estoques/listar")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("estoques/lista");
        mv.addObject("estoques", estoqueRepository.findAll());
        return mv;
    }


    @GetMapping(path="/estoques/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id){
        Optional<Estoque> estoque = estoqueRepository.findById(id);
        return cadastrar(estoque.get());
    }


    @GetMapping(path="/estoques/remover/{id}")
    public ModelAndView remover(@PathVariable("id") Integer id) {
        Optional<Estoque> estoque = estoqueRepository.findById(id);
        estoqueRepository.delete(estoque.get());
        return listar();
    }


    @PostMapping(path="/estoques/salvar")
    public ModelAndView salvar(Estoque estoque, BindingResult result){
        System.out.println(estoque.getPreco());
        if (result.hasErrors()){
            System.out.println(result.toString());
            return cadastrar(estoque);
        }
        estoqueRepository.saveAndFlush(estoque);
        return cadastrar(new Estoque());
    }


}
