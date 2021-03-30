package com.estoque.Desafio.controller;

import com.estoque.Desafio.models.Filial;
import com.estoque.Desafio.repository.FilialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class FilialController {

    @Autowired
    private FilialRepository filialRepository;

    @GetMapping(path="/filiais/cadastrar")
    public ModelAndView cadastrar(Filial filial){
        ModelAndView mv = new ModelAndView("filiais/cadastro");
        mv.addObject("filial", filial);
        return mv;
    }


    @GetMapping(path="/filiais/listar")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("filiais/lista");
        mv.addObject("filiais", filialRepository.findAll());
        return mv;
    }


    @GetMapping(path="/filiais/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id){
        Optional<Filial> filial = filialRepository.findById(id);
        return cadastrar(filial.get());
    }


    @GetMapping(path="/filiais/remover/{id}")
    public ModelAndView remover(@PathVariable("id") Integer id) {
        Optional<Filial> filial = filialRepository.findById(id);
        filialRepository.delete(filial.get());
        return listar();
    }


    @PostMapping(path="/filiais/salvar")
    public ModelAndView salvar(Filial filial, BindingResult result){
        if (result.hasErrors()){
            return cadastrar(filial);
        }
        filialRepository.saveAndFlush(filial);
        return cadastrar(new Filial());
    }


}
