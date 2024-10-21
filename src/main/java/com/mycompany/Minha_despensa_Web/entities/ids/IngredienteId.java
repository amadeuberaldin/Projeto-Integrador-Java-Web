package com.mycompany.Minha_despensa_Web.entities.ids;

import java.io.Serializable;
import java.util.Objects;

public class IngredienteId implements Serializable {

    private Long receita;
    private Long produto;

    public IngredienteId() {}

    public IngredienteId(Long receita, Long produto) {
        this.receita = receita;
        this.produto = produto;
    }

    public Long getReceita() {
        return receita;
    }

    public void setReceita(Long receita) {
        this.receita = receita;
    }

    public Long getProduto() {
        return produto;
    }

    public void setProduto(Long produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredienteId that = (IngredienteId) o;
        return Objects.equals(receita, that.receita) && Objects.equals(produto, that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(receita, produto);
    }
}