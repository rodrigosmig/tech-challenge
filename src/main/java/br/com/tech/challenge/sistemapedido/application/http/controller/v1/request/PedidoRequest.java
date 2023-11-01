package br.com.tech.challenge.sistemapedido.application.http.controller.v1.request;

import br.com.tech.challenge.sistemapedido.application.http.controller.v1.dto.ItemProdutoDTO;

import java.util.List;

public record PedidoRequest(List<ItemProdutoDTO> itens) {}
