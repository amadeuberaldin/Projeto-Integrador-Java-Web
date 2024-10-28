package com.mycompany.Minha_despensa_Web.controllers;

import com.mycompany.Minha_despensa_Web.entities.Receita;
import com.mycompany.Minha_despensa_Web.entities.Usuario;
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
}
