package com.estoque.Desafio.controller;

import com.estoque.Desafio.models.Cliente;
import com.estoque.Desafio.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping(path="/clientes/cadastrar")
    public ModelAndView cadastrar(Cliente cliente){
        ModelAndView mv = new ModelAndView("clientes/cadastro");
        mv.addObject("cliente", cliente);
        return mv;
    }


    @GetMapping(path="/clientes/listar")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("clientes/lista");
        mv.addObject("clientes", clienteRepository.findAll());
        return mv;
    }


    @GetMapping(path="/clientes/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cadastrar(cliente.get());
    }


    @GetMapping(path="/clientes/remover/{id}")
    public ModelAndView remover(@PathVariable("id") Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        clienteRepository.delete(cliente.get());
        return listar();
    }


    @PostMapping(path="/clientes/salvar")
    public ModelAndView salvar(Cliente cliente, BindingResult result){
        if (result.hasErrors()){
            return cadastrar(cliente);
        }
        clienteRepository.saveAndFlush(cliente);
        return cadastrar(new Cliente());
    }


}
