package com.mycompany.Minha_despensa_Web.services;

import com.mycompany.Minha_despensa_Web.entities.Ingrediente;
import com.mycompany.Minha_despensa_Web.entities.Produto;
import com.mycompany.Minha_despensa_Web.entities.Receita;
import com.mycompany.Minha_despensa_Web.repositories.ProdutoRepository;
import com.mycompany.Minha_despensa_Web.repositories.ReceitaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Receita salvarReceita(Receita receita) {
        for (Ingrediente ingrediente : receita.getIngredientes()) {
            // Valida se o produto existe e associa ao ingrediente
            if (ingrediente.getProduto() == null || ingrediente.getProduto().getId() == null) {
                throw new RuntimeException("Produto não informado para o ingrediente.");
            }
            Produto produto = produtoRepository.findById(ingrediente.getProduto().getId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + ingrediente.getProduto().getId()));
            ingrediente.setProduto(produto);
        }
        return receitaRepository.save(receita);
    }

    public List<Receita> listarReceitasPorUsuario(Long usuarioId) {
        return receitaRepository.findByUsuarioId(usuarioId);
    }

    public void deletarReceita(Long id) {
        receitaRepository.deleteById(id);
    }

    public List<Receita> listarTodas() {
        return receitaRepository.findAll();
    }
}
