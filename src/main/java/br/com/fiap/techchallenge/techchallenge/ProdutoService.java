package br.com.fiap.techchallenge.techchallenge;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Collection<ProdutoEntity> findAll() {
        var produtos = produtoRepository.findAll();
        return produtos;
    }

    public ProdutoEntity findById(UUID id) {
        var produto = produtoRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Produto não encontrado."));
        return produto;
    }

    public ProdutoEntity save(ProdutoEntity produto) {
        produto = produtoRepository.save(produto);
        return produto;
    }

    public ProdutoEntity updateById(UUID id, ProdutoEntity produto) {
        try {
            ProdutoEntity buscaProduto = produtoRepository.getOne(id);
            buscaProduto.setNome(produto.getNome());
            buscaProduto.setDescricao(produto.getDescricao());
            buscaProduto.setPreco(produto.getPreco());
            buscaProduto.setUrlDaImagem(produto.getUrlDaImagem());
            buscaProduto = produtoRepository.save(buscaProduto);
            return buscaProduto;
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Produto não encontrado.");
        }
    }

    public void deleteById(UUID id) {
        produtoRepository.deleteById(id);
    }

}
