package br.com.tech.challenge.sistemapedido.application.http.controller.v1.dto;

import br.com.tech.challenge.sistemapedido.core.domain.Categoria;

import java.math.BigDecimal;

public record ItemPedidoDTO(String nomeProduto,
                            String descricaoProduto,
                            Categoria categoriaProduto,
                            BigDecimal preco,
                            Integer quantidade,
                            String observacao) {
}
