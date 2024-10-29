package com.mycompany.Minha_despensa_Web.entities.DTO;

public class ProdutoDTO {

    private Long id;
    private String nome;
    private Double porcaoReferencia;
    private Double proteinas;
    private Double carboidratos;
    private Double calorias;
    private Double gordurasTotais;

    // Construtor completo para criar ProdutoDTO
    public ProdutoDTO(Long id, String nome, Double porcaoReferencia, Double proteinas,
                      Double carboidratos, Double calorias, Double gordurasTotais) {
        this.id = id;
        this.nome = nome;
        this.porcaoReferencia = porcaoReferencia;
        this.proteinas = proteinas;
        this.carboidratos = carboidratos;
        this.calorias = calorias;
        this.gordurasTotais = gordurasTotais;
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

    public Double getPorcaoReferencia() {
        return porcaoReferencia;
    }

    public void setPorcaoReferencia(Double porcaoReferencia) {
        this.porcaoReferencia = porcaoReferencia;
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

    // Interface ProdutoProjection para limitar os dados retornados pelo repositório
    public interface ProdutoProjection {

        Long getId();

        String getNome();

        Double getPorcaoReferencia();

        Double getProteinas();

        Double getCarboidratos();

        Double getCalorias();

        Double getGordurasTotais();
    }
}
