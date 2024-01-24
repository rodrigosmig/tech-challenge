package br.com.tech.challenge.sistemapedido.usecase.contract.pedido;

import java.io.File;

public interface PagarPedidoUseCase {
    void pagar(Long idPedido);
    File gerarPagamento(Long idPedido);
}
