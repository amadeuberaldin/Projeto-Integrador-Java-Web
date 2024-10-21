package com.mycompany.Minha_despensa_Web.controllers;


import com.mycompany.Minha_despensa_Web.entities.Despensa;
import com.mycompany.Minha_despensa_Web.services.DespensaService;
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
@RequestMapping("/despensa")
public class DespensaController {

    @Autowired
    private DespensaService despensaService;

    @PostMapping("/salvar")
    public ResponseEntity<Despensa> salvarDespensa(@RequestBody Despensa despensa) {
        Despensa novaDespensa = despensaService.salvarDespensa(despensa);
        return new ResponseEntity<>(novaDespensa, HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Despensa> listarDespensaPorUsuario(@PathVariable Long usuarioId) {
        return despensaService.listarDespensaPorUsuario(usuarioId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDespensa(@PathVariable Long id) {
        despensaService.deletarDespensa(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
