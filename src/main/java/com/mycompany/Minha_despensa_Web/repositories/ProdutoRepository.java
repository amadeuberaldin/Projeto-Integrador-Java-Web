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


    @Query("SELECT p.id AS id, p.nome AS nome, p.porcaoReferencia AS porcaoReferencia, " +
           "p.proteinas AS proteinas, p.carboidratos AS carboidratos, " +
           "p.calorias AS calorias, p.gordurasTotais AS gordurasTotais " +
           "FROM Produto p WHERE p.id = :id")
    ProdutoProjection findProdutoById(@Param("id") Long id);
}