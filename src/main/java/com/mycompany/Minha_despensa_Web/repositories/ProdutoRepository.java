package com.mycompany.Minha_despensa_Web.repositories;

import com.mycompany.Minha_despensa_Web.entities.Produto;
import com.mycompany.Minha_despensa_Web.entities.DTO.ProdutoDTO.ProdutoProjection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByNomeContaining(String nome);

    @Query("SELECT p.id as id, p.nome as nome, p.porcaoReferencia as porcaoReferencia, "
            + "p.proteinas as proteinas, p.carboidratos as carboidratos, "
            + "p.calorias as calorias, p.gordurasTotais as gordurasTotais "
            + "FROM Produto p WHERE p.id = :id")
    ProdutoProjection findProdutoById(@Param("id") Long id);
}
