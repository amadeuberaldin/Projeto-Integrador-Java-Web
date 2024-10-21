package com.mycompany.Minha_despensa_Web.services;

import com.mycompany.Minha_despensa_Web.entities.Receita;
import com.mycompany.Minha_despensa_Web.repositories.ReceitaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    public Receita salvarReceita(Receita receita) {
        return receitaRepository.save(receita);
    }

    public List<Receita> listarReceitasPorUsuario(Long usuarioId) {
        return receitaRepository.findByUsuarioId(usuarioId);
    }

    public void deletarReceita(Long id) {
        receitaRepository.deleteById(id);
    }

    public List<Receita> listarTodas() {
        return receitaRepository.findAll();
    }
}
