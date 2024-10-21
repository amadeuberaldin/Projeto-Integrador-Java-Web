package com.mycompany.Minha_despensa_Web.controllers;

import com.mycompany.Minha_despensa_Web.entities.Receita;
import com.mycompany.Minha_despensa_Web.services.ReceitaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @PostMapping("/salvar")
    public ResponseEntity<Receita> salvarReceita(@RequestBody Receita receita) {
        Receita novaReceita = receitaService.salvarReceita(receita);
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
