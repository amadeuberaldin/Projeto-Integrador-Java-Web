package com.mycompany.Minha_despensa_Web.controllers;

import com.mycompany.Minha_despensa_Web.entities.DTO.ReceitaDTO;
import com.mycompany.Minha_despensa_Web.entities.Ingrediente;
import com.mycompany.Minha_despensa_Web.entities.Produto;
import com.mycompany.Minha_despensa_Web.entities.Receita;
import com.mycompany.Minha_despensa_Web.entities.Usuario;
import com.mycompany.Minha_despensa_Web.services.ProdutoService;
import com.mycompany.Minha_despensa_Web.services.ReceitaService;
import com.mycompany.Minha_despensa_Web.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(value = "/salvar", consumes = "application/json")
    public ResponseEntity<Receita> salvarReceita(@RequestBody Receita receita) {
        Usuario usuarioAtual = usuarioService.obterUsuarioAutenticado();

        if (usuarioAtual == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        receita.setUsuario(usuarioAtual); // Associa a receita ao usuário autenticado

        // Processa e salva a receita com seus ingredientes
        Receita novaReceita = receitaService.salvarReceitaComIngredientes(receita);

        return new ResponseEntity<>(novaReceita, HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Receita> listarReceitasPorUsuario(@PathVariable Long usuarioId) {
        return receitaService.listarReceitasPorUsuario(usuarioId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarReceita(@PathVariable Long id) {
        receitaService.deletarReceita(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceitaDTO> getReceitaById(@PathVariable Long id) {
        System.out.println("Buscando receita com ID: " + id); // Log para depuração
        ReceitaDTO receita = receitaService.findReceitaById(id);
        return ResponseEntity.ok(receita);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receita> atualizarReceita(@PathVariable Long id, @RequestBody ReceitaDTO receitaDTO) {
        Receita receitaExistente = receitaService.findById(id);

        if (receitaExistente == null) {
            return ResponseEntity.notFound().build();
        }

        // Atualiza os campos básicos da receita
        receitaExistente.setNome(receitaDTO.getNome());
        receitaExistente.setModoPreparo(receitaDTO.getModoPreparo());

        // Limpa os ingredientes antigos e adiciona os novos da DTO
        receitaExistente.getIngredientes().clear();
        receitaDTO.getIngredientes().forEach(ingredienteDTO -> {
            Produto produto = produtoService.findById(ingredienteDTO.getProdutoId());

            if (produto != null) {
                Ingrediente ingrediente = new Ingrediente();
                ingrediente.setProduto(produto);
                ingrediente.setQuantidade(ingredienteDTO.getQuantidade());
                ingrediente.setReceita(receitaExistente);
                receitaExistente.getIngredientes().add(ingrediente);
            }
        });

        // Salva e retorna a receita atualizada
        Receita receitaAtualizada = receitaService.salvar(receitaExistente);
        return ResponseEntity.ok(receitaAtualizada);
    }
}
