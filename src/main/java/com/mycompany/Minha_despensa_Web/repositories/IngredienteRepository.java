package com.mycompany.Minha_despensa_Web.repositories;

import com.mycompany.Minha_despensa_Web.entities.Ingrediente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mycompany.Minha_despensa_Web.entities.ids.IngredienteId;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, IngredienteId> {

    List<Ingrediente> findByReceitaId(Long receitaId);
}
