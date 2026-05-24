package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model.Usuario;
import com.model.UsuarioService;

@Controller
public class MenuController {

    @Autowired
    ApplicationContext context;

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/cadastro")
    public String cadastroPage(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }

    @PostMapping("/usuario")
    public String cadastrarUsuario(@ModelAttribute Usuario usuario) {
        UsuarioService us = context.getBean(UsuarioService.class);
        us.inserir(usuario);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String autenticar(@RequestParam String email,
                             @RequestParam String senha) {
        UsuarioService us = context.getBean(UsuarioService.class);
        if (us.login(email, senha)) {
            return "redirect:/home";
        }
        return "redirect:/login?erro=true";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}