package com.mycompany.Minha_despensa_Web.services;

import com.mycompany.Minha_despensa_Web.entities.Produto;
import com.mycompany.Minha_despensa_Web.repositories.ProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public List<Produto> buscarPorNome(String nome) {
        return produtoRepository.findByNomeContaining(nome);
    }

    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    public Produto findById(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }
}
