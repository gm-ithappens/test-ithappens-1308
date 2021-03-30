package com.estoque.Desafio.controller;

import com.estoque.Desafio.models.Usuario;
import com.estoque.Desafio.repository.FilialRepository;
import com.estoque.Desafio.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private FilialRepository filialRepository;

    @GetMapping(path="/usuarios/cadastrar")
    public ModelAndView cadastrar(Usuario usuario){
        ModelAndView mv = new ModelAndView("usuarios/cadastro");
        mv.addObject("usuario", usuario);
        /* Listagem de Filiais */
        mv.addObject("filiais", filialRepository.findAll());
        return mv;
    }

    @PostMapping(path="/usuarios/salvar")
    public ModelAndView salvar(@Valid Usuario usuario, BindingResult result){
        if (result.hasErrors()){
            return cadastrar(usuario);
        }
        usuarioRepository.saveAndFlush(usuario);
        return cadastrar(new Usuario());
    }

    @GetMapping(path="/usuarios/listar")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("usuarios/lista");
        mv.addObject("usuarios", usuarioRepository.findAll());
        return mv;
    }

    @GetMapping(path="/usuarios/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return cadastrar(usuario.get());
    }

    @GetMapping(path="/usuarios/remover/{id}")
    public ModelAndView remover(@PathVariable("id") Integer id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        usuarioRepository.delete(usuario.get());
        return listar();
    }

}
