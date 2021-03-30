package com.estoque.Desafio.controller;

import com.estoque.Desafio.models.FormaPagamento;
import com.estoque.Desafio.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @GetMapping(path="/forma_pagamento/cadastrar")
    public ModelAndView cadastrar(FormaPagamento formaPagamento){
        ModelAndView mv = new ModelAndView("formas_pagamentos/cadastro");
        mv.addObject("formaPagamento", formaPagamento);
        return mv;
    }


    @GetMapping(path="/forma_pagamento/listar")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("formas_pagamentos/lista");
        mv.addObject("formaspagamentos", formaPagamentoRepository.findAll());
        return mv;
    }


    @GetMapping(path="/forma_pagamento/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id){
        Optional<FormaPagamento> formaPagamento = formaPagamentoRepository.findById(id);
        return cadastrar(formaPagamento.get());
    }


    @GetMapping(path="/forma_pagamento/remover/{id}")
    public ModelAndView remover(@PathVariable("id") Integer id) {
        Optional<FormaPagamento> formaPagamento = formaPagamentoRepository.findById(id);
        formaPagamentoRepository.delete(formaPagamento.get());
        return listar();
    }


    @PostMapping(path="/forma_pagamento/salvar")
    public ModelAndView salvar(FormaPagamento formaPagamento, BindingResult result){
        if (result.hasErrors()){
            return cadastrar(formaPagamento);
        }
        formaPagamentoRepository.saveAndFlush(formaPagamento);
        return cadastrar(new FormaPagamento());
    }


}
