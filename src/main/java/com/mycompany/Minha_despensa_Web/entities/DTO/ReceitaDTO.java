package com.mycompany.Minha_despensa_Web.entities.DTO;

import java.util.List;

public class ReceitaDTO {
    private Long id;
    private String nome;
    private String modoPreparo;
    private List<IngredienteDTO> ingredientes;

    public ReceitaDTO(Long id, String nome, String modoPreparo, List<IngredienteDTO> ingredientes) {
        this.id = id;
        this.nome = nome;
        this.modoPreparo = modoPreparo;
        this.ingredientes = ingredientes;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModoPreparo() {
        return modoPreparo;
    }

    public void setModoPreparo(String modoPreparo) {
        this.modoPreparo = modoPreparo;
    }

    public List<IngredienteDTO> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<IngredienteDTO> ingredientes) {
        this.ingredientes = ingredientes;
    }

    // Classe interna para representar o ingrediente simplificado
    public static class IngredienteDTO {

        private Long produtoId;
        private String produtoNome;
        private Double quantidade;

        public IngredienteDTO(Long produtoId, String produtoNome, Double quantidade) {
            this.produtoId = produtoId;
            this.produtoNome = produtoNome;
            this.quantidade = quantidade;
        }

        // Getters e Setters
        public Long getProdutoId() {
            return produtoId;
        }

        public void setProdutoId(Long produtoId) {
            this.produtoId = produtoId;
        }

        public String getProdutoNome() {
            return produtoNome;
        }

        public void setProdutoNome(String produtoNome) {
            this.produtoNome = produtoNome;
        }

        public Double getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(Double quantidade) {
            this.quantidade = quantidade;
        }
    }
}
