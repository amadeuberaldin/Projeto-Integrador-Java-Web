package com.mycompany.Minha_despensa_Web.controllers;

import com.mycompany.Minha_despensa_Web.entities.Usuario;
import com.mycompany.Minha_despensa_Web.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Exibe o formulário de cadastro
    @GetMapping("/usuario/cadastro")
    public String showCadastroForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cadastro";
    }

    // Salva o usuário no banco de dados
    @PostMapping("/usuario/salvar")
    public String cadastrarUsuario(Usuario usuario) {
        usuarioService.cadastrarUsuario(usuario);
        return "redirect:/login";
    }
}
