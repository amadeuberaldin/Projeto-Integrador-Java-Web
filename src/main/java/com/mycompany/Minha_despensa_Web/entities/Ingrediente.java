package com.mycompany.Minha_despensa_Web.entities;

import com.mycompany.Minha_despensa_Web.entities.ids.IngredienteId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ingrediente")
@IdClass(IngredienteId.class)
public class Ingrediente {

    @Id
    @ManyToOne
    @JoinColumn(name = "receita_id", nullable = false)
    private Receita receita;

    @Id
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    private Float quantidade;

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Float quantidade) {
        this.quantidade = quantidade;
    }
}
