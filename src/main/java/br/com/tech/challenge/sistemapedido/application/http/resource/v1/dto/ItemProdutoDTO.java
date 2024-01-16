package br.com.tech.challenge.sistemapedido.application.http.resource.v1.dto;

public record ItemProdutoDTO(Long idProduto,
                             Integer quantidade,
                             String observacao) {}
