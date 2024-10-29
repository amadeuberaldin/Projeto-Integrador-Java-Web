package com.mycompany.Minha_despensa_Web.entities.DTO;

public class IngredienteDTO {
    private Long produtoId;
    private String produtoNome;
    private Double quantidade;
    private Double proteinas;
    private Double carboidratos;
    private Double calorias;
    private Double gordurasTotais;

    // Construtor ajustado
    public IngredienteDTO(Long produtoId, String produtoNome, Double quantidade, 
                          Double proteinas, Double carboidratos, 
                          Double calorias, Double gordurasTotais) {
        this.produtoId = produtoId;
        this.produtoNome = produtoNome;
        this.quantidade = quantidade;
        this.proteinas = proteinas;
        this.carboidratos = carboidratos;
        this.calorias = calorias;
        this.gordurasTotais = gordurasTotais;
    }

    // Getters e setters
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

    public Double getProteinas() {
        return proteinas;
    }

    public void setProteinas(Double proteinas) {
        this.proteinas = proteinas;
    }

    public Double getCarboidratos() {
        return carboidratos;
    }

    public void setCarboidratos(Double carboidratos) {
        this.carboidratos = carboidratos;
    }

    public Double getCalorias() {
        return calorias;
    }

    public void setCalorias(Double calorias) {
        this.calorias = calorias;
    }

    public Double getGordurasTotais() {
        return gordurasTotais;
    }

    public void setGordurasTotais(Double gordurasTotais) {
        this.gordurasTotais = gordurasTotais;
    }
}
