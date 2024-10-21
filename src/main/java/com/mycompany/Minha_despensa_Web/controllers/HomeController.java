package com.mycompany.Minha_despensa_Web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.mycompany.Minha_despensa_Web.entities.Produto;
import com.mycompany.Minha_despensa_Web.entities.Receita;
import com.mycompany.Minha_despensa_Web.services.ProdutoService;
import com.mycompany.Minha_despensa_Web.services.ReceitaService;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ReceitaService receitaService;

    @GetMapping("/home")
    public String home(Model model) {
        // Lista de produtos
        List<Produto> produtos = produtoService.listarProdutos();
        model.addAttribute("produtos", produtos);

        // Inicializa um novo objeto de Produto para o formulário de cadastro
        model.addAttribute("produto", new Produto());

        // Lista de receitas
        List<Receita> receitas = receitaService.listarTodas(); 
        model.addAttribute("receitas", receitas);

        // Inicializa um novo objeto de Receita para o formulário de cadastro
        model.addAttribute("receita", new Receita());

        return "home";  // Retorna a página home.html
    }
}
