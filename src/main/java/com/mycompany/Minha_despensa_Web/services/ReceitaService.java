package com.mycompany.Minha_despensa_Web.services;

import com.mycompany.Minha_despensa_Web.entities.DTO.ReceitaDTO;
import com.mycompany.Minha_despensa_Web.entities.Ingrediente;
import com.mycompany.Minha_despensa_Web.entities.Produto;
import com.mycompany.Minha_despensa_Web.entities.Receita;
import com.mycompany.Minha_despensa_Web.repositories.ReceitaRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private ProdutoService produtoService;

    public Receita salvarReceitaComIngredientes(Receita receita) {
        for (Ingrediente ingrediente : receita.getIngredientes()) {
            Long produtoId = ingrediente.getProduto().getId();

            Produto produto = produtoService.findById(produtoId);
            if (produto == null) {
                throw new IllegalArgumentException("Produto não encontrado para o ID: " + produtoId);
            }

            ingrediente.setProduto(produto);
            ingrediente.setReceita(receita);
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

    public ReceitaDTO findReceitaById(Long id) {
        Receita receita = receitaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Receita não encontrada"));

        List<ReceitaDTO.IngredienteDTO> ingredientes = receita.getIngredientes().stream()
                .map(ing -> new ReceitaDTO.IngredienteDTO(
                ing.getProduto().getId(),
                ing.getProduto().getNome(),
                ing.getQuantidade()
        ))
                .collect(Collectors.toList());

        return new ReceitaDTO(receita.getId(), receita.getNome(), receita.getModoPreparo(), ingredientes);
    }

    // Método para encontrar uma receita por ID
    public Receita findById(Long id) {
        Optional<Receita> receitaOptional = receitaRepository.findById(id);
        return receitaOptional.orElse(null);
    }

    public Receita salvar(Receita receita) {
        receita.getIngredientes().forEach(ingrediente -> ingrediente.setReceita(receita)); 
        return receitaRepository.save(receita);
    }
}
