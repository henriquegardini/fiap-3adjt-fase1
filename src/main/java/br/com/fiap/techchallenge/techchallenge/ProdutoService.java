package br.com.fiap.techchallenge.techchallenge;

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

    public Optional<ProdutoEntity> findById(UUID id) {
        var produto = produtoRepository.findById(id);
        return produto;
    }

    public ProdutoEntity save(ProdutoEntity produto) {
        produto = produtoRepository.save(produto);
        return produto;
    }

    public ProdutoEntity updateById(UUID id, ProdutoEntity produto) {
        ProdutoEntity buscaProduto = produtoRepository.getOne(id);
        buscaProduto.setNome(produto.getNome());
        buscaProduto.setDescricao(produto.getDescricao());
        buscaProduto.setPreco(produto.getPreco());
        buscaProduto.setUrlDaImagem(produto.getUrlDaImagem());
        buscaProduto = produtoRepository.save(buscaProduto);
        return buscaProduto;
    }

    public void deleteById(UUID id) {
        produtoRepository.deleteById(id);
    }

}
