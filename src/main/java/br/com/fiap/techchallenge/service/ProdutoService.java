package br.com.fiap.techchallenge.service;

import br.com.fiap.techchallenge.controller.exception.ControllerNotFoundException;
import br.com.fiap.techchallenge.dto.ProdutoDTO;
import br.com.fiap.techchallenge.entities.ProdutoEntity;
import br.com.fiap.techchallenge.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Collection<ProdutoDTO> findAll() {
        var produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(this::toProdutoDTO)
                .collect(Collectors.toList());
    }

    public ProdutoDTO findById(UUID id) {
        var produto = produtoRepository.findById(id).orElseThrow(() -> new ControllerNotFoundException("Produto não encontrado."));
        return toProdutoDTO(produto);
    }

    public ProdutoDTO save(ProdutoDTO produtoDTO) {
        ProdutoEntity produtoEntity = toProdutoEntity(produtoDTO);
        produtoEntity = produtoRepository.save(produtoEntity);
        return toProdutoDTO(produtoEntity);
    }

    public ProdutoDTO updateById(UUID id, ProdutoDTO produtoDTO) {
        try {
            ProdutoEntity buscaProduto = produtoRepository.getReferenceById(id);
            buscaProduto.setNome(produtoDTO.nome());
            buscaProduto.setDescricao(produtoDTO.descricao());
            buscaProduto.setPreco(produtoDTO.preco());
            buscaProduto.setUrlDaImagem(produtoDTO.urlDaImagem());
            buscaProduto = produtoRepository.save(buscaProduto);
            return toProdutoDTO(buscaProduto);
        } catch (EntityNotFoundException e) {
            throw new ControllerNotFoundException("Produto não encontrado.");
        }
    }

    public void deleteById(UUID id) {
        produtoRepository.deleteById(id);
    }

    private ProdutoDTO toProdutoDTO(ProdutoEntity produto) {
        return new ProdutoDTO(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getUrlDaImagem()
        );
    }

    private ProdutoEntity toProdutoEntity(ProdutoDTO produto) {
        return new ProdutoEntity(
                produto.id(),
                produto.nome(),
                produto.descricao(),
                produto.preco(),
                produto.urlDaImagem()
        );
    }

}
