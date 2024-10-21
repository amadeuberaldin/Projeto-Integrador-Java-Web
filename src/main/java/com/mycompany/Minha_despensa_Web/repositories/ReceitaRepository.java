package com.mycompany.Minha_despensa_Web.repositories;

import com.mycompany.Minha_despensa_Web.entities.Receita;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    List<Receita> findByUsuarioId(Long usuarioId);
}
