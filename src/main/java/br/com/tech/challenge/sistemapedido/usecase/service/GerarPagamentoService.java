package br.com.tech.challenge.sistemapedido.usecase.service;

import br.com.tech.challenge.sistemapedido.domain.Pedido;

public interface GerarPagamentoService {
    void gerar(Pedido pedido);

}
