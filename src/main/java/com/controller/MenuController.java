
package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.model.Usuario;
import com.model.UsuarioService;

import ch.qos.logback.core.model.Model;

@Controller
public class MenuController {

    @Autowired
    ApplicationContext context;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/usuario")
    public String formUsuario(Model model) {
       model.addAttribute("usuario", new Usuario());
       return "usuario"; 
    }

    @PostMapping("/usuario")
    public String formUsuario(@ModelAttribute Usuario usuario, Model model) {
        UsuarioService us = context.getBean(UsuarioService.class);
        us.inserir(usuario);
        return "sucesso";
    }
}
