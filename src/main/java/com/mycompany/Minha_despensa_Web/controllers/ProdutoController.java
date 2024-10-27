package com.mycompany.Minha_despensa_Web.controllers;

import com.mycompany.Minha_despensa_Web.entities.Produto;
import com.mycompany.Minha_despensa_Web.entities.Usuario;
import com.mycompany.Minha_despensa_Web.services.ProdutoService;
import com.mycompany.Minha_despensa_Web.services.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private UsuarioService usuarioService; // Injetando o UsuarioService

    @PostMapping("/salvar")
    public String salvarProduto(@ModelAttribute Produto produto, RedirectAttributes redirectAttributes) {
        Usuario usuarioAtual = usuarioService.obterUsuarioAutenticado();
        produto.setUsuario(usuarioAtual);
        produtoService.salvarProduto(produto);

        // Adiciona uma mensagem de confirmação (opcional)
        redirectAttributes.addFlashAttribute("mensagem", "Produto salvo com sucesso!");

        // Redireciona para a página inicial
        return "redirect:/home";
    }

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }

    @GetMapping("/buscar/{nome}")
    public List<Produto> buscarPorNome(@PathVariable String nome) {
        return produtoService.buscarPorNome(nome);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {
        Produto produto = produtoService.findById(id);
        if (produto != null) {
            return ResponseEntity.ok(produto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Método PUT para atualizar o produto
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
        Produto produtoExistente = produtoService.findById(id);
        if (produtoExistente != null) {
            // Atualiza os campos do produto existente com os valores do produto atualizado
            produtoExistente.setNome(produtoAtualizado.getNome());
            produtoExistente.setPorcaoReferencia(produtoAtualizado.getPorcaoReferencia());
            produtoExistente.setProteinas(produtoAtualizado.getProteinas());
            produtoExistente.setCarboidratos(produtoAtualizado.getCarboidratos());
            produtoExistente.setCalorias(produtoAtualizado.getCalorias());
            produtoExistente.setGordurasTotais(produtoAtualizado.getGordurasTotais());

            // Salva as alterações
            produtoService.salvarProduto(produtoExistente);
            return ResponseEntity.ok(produtoExistente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
