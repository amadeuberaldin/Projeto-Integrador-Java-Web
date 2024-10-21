package com.mycompany.Minha_despensa_Web.services;

import com.mycompany.Minha_despensa_Web.entities.Despensa;
import com.mycompany.Minha_despensa_Web.repositories.DespensaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DespensaService {

    @Autowired
    private DespensaRepository despensaRepository;

    public Despensa salvarDespensa(Despensa despensa) {
        return despensaRepository.save(despensa);
    }

    public List<Despensa> listarDespensaPorUsuario(Long usuarioId) {
        return despensaRepository.findByUsuarioId(usuarioId);
    }

    public void deletarDespensa(Long id) {
        despensaRepository.deleteById(id);
    }
}
