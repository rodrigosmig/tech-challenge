package br.com.tech.challenge.sistemapedido.usecase.service;

import br.com.tech.challenge.sistemapedido.domain.Pedido;

import java.io.File;

public interface GerarPagamentoService {
    File gerarQrCode(Pedido pedido);
}
