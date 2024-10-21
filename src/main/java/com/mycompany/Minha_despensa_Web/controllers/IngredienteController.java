package com.mycompany.Minha_despensa_Web.controllers;

import com.mycompany.Minha_despensa_Web.entities.Ingrediente;
import com.mycompany.Minha_despensa_Web.services.IngredienteService;
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
import com.mycompany.Minha_despensa_Web.entities.ids.IngredienteId;

@RestController
@RequestMapping("/ingredientes")
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService;

    @PostMapping("/salvar")
    public ResponseEntity<Ingrediente> salvarIngrediente(@RequestBody Ingrediente ingrediente) {
        Ingrediente novoIngrediente = ingredienteService.salvarIngrediente(ingrediente);
        return new ResponseEntity<>(novoIngrediente, HttpStatus.CREATED);
    }

    @GetMapping("/receita/{receitaId}")
    public List<Ingrediente> listarIngredientesPorReceita(@PathVariable Long receitaId) {
        return ingredienteService.listarIngredientesPorReceita(receitaId);
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<Void> deletarIngrediente(@RequestBody IngredienteId id) {
        ingredienteService.deletarIngrediente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
