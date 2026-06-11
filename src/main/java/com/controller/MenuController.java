package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model.MensagemChatService;
import com.model.Noticia;
import com.model.NoticiaService;
import com.model.Usuario;
import com.model.UsuarioService;

import jakarta.servlet.http.HttpSession;

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
    public String cadastrarUsuario(@RequestParam String nome,
                                   @RequestParam String email,
                                   @RequestParam String senha) {
        UsuarioService us = context.getBean(UsuarioService.class);
        Usuario usuario = new Usuario();
        usuario.setNome(new com.model.Nome(nome));
        usuario.setEmail(new com.model.Email(email));
        usuario.setSenha(new com.model.Senha(senha));
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

    @GetMapping("/membros")
    public String membros() {
        return "membros";
    }

    @GetMapping("/tour")
    public String tour() {
        return "tour";
    }

    @GetMapping("/noticias")
    public String noticias(Model model) {
        NoticiaService ns = context.getBean(NoticiaService.class);
        List<Noticia> lista = ns.obterTodasNoticias();
        model.addAttribute("noticias", lista);
        return "noticias";
    }

    @GetMapping("/noticia/{id}")
    public String detalheNoticia(@PathVariable int id, Model model) {
        NoticiaService ns = context.getBean(NoticiaService.class);
        Noticia noticia = ns.obterNoticia(id);
        model.addAttribute("noticia", noticia);
        return "detalhe-noticia";
    }

    @GetMapping("/chat")
    public String chatPage(Model model, HttpSession session) {
        MensagemChatService cs = context.getBean(MensagemChatService.class);
        var mensagens = cs.obterTodas();
        String sid = session.getId();
        var curtidas = mensagens.stream()
            .filter(m -> cs.curtidaExiste(m.id(), sid))
            .map(m -> m.id())
            .collect(java.util.stream.Collectors.toSet());
        model.addAttribute("mensagens", mensagens);
        model.addAttribute("curtidas", curtidas);
        model.addAttribute("sessionId", sid);
        return "chat";
    }

    @PostMapping("/chat")
    public String enviarMensagem(@RequestParam String conteudo,
                                 @RequestParam(required = false) Long respostaId) {
        MensagemChatService cs = context.getBean(MensagemChatService.class);
        cs.enviar(conteudo, respostaId);
        return "redirect:/chat";
    }

    @PostMapping("/chat/curtir/{id}")
    public String curtir(@PathVariable Long id, HttpSession session) {
        MensagemChatService cs = context.getBean(MensagemChatService.class);
        cs.toggleCurtida(id, session.getId());
        return "redirect:/chat";
    }
}