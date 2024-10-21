package com.mycompany.Minha_despensa_Web.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(name = "porcao_referencia", nullable = false)
    private Float porcaoReferencia;

    @Column(nullable = false)
    private Float proteinas;

    @Column(nullable = false)
    private Float carboidratos;

    @Column(nullable = false)
    private Float calorias;

    @Column(name = "gorduras_totais", nullable = false)
    private Float gordurasTotais;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ingrediente> ingredientes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getPorcaoReferencia() {
        return porcaoReferencia;
    }

    public void setPorcaoReferencia(Float porcaoReferencia) {
        this.porcaoReferencia = porcaoReferencia;
    }

    public Float getProteinas() {
        return proteinas;
    }

    public void setProteinas(Float proteinas) {
        this.proteinas = proteinas;
    }

    public Float getCarboidratos() {
        return carboidratos;
    }

    public void setCarboidratos(Float carboidratos) {
        this.carboidratos = carboidratos;
    }

    public Float getCalorias() {
        return calorias;
    }

    public void setCalorias(Float calorias) {
        this.calorias = calorias;
    }

    public Float getGordurasTotais() {
        return gordurasTotais;
    }

    public void setGordurasTotais(Float gordurasTotais) {
        this.gordurasTotais = gordurasTotais;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }
}
