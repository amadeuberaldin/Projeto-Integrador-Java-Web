package com.mycompany.Minha_despensa_Web.services;

import com.mycompany.Minha_despensa_Web.entities.Ingrediente;
import com.mycompany.Minha_despensa_Web.repositories.IngredienteRepository;
import com.mycompany.Minha_despensa_Web.entities.ids.IngredienteId;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredienteService {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    public List<Ingrediente> listarIngredientesPorReceita(Long receitaId) {
        return ingredienteRepository.findByReceitaId(receitaId);
    }

    public Ingrediente salvarIngrediente(Ingrediente ingrediente) {
        return ingredienteRepository.save(ingrediente);
    }

    public void deletarIngrediente(IngredienteId id) {
        ingredienteRepository.deleteById(id);
    }
}
