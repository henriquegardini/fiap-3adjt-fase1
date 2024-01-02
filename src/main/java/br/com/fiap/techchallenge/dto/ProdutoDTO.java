package br.com.fiap.techchallenge.dto;

import java.util.UUID;

public record ProdutoDTO(
        UUID id,
        String nome,
        String descricao,
        double preco,
        String urlDaImagem
) {
}
