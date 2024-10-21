package com.mycompany.Minha_despensa_Web.repositories;

import com.mycompany.Minha_despensa_Web.entities.Despensa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespensaRepository extends JpaRepository<Despensa, Long> {

    List<Despensa> findByUsuarioId(Long usuarioId);
}
