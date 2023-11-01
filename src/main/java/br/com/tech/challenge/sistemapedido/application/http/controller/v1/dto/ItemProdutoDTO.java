package br.com.tech.challenge.sistemapedido.application.http.controller.v1.dto;

public record ItemProdutoDTO(Long idProduto,
                             Integer quantidade,
                             String observacao) {}
