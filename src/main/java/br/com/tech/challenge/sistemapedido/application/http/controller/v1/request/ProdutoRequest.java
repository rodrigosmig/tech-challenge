package br.com.tech.challenge.sistemapedido.application.http.controller.v1.request;

import java.math.BigDecimal;

public record ProdutoRequest(String nome,
                             String categoria,
                             String descricao,
                             BigDecimal preco) {
}
