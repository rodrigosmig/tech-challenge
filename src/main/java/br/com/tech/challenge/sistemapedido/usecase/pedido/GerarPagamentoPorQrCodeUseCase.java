package br.com.tech.challenge.sistemapedido.usecase.pedido;

import br.com.tech.challenge.sistemapedido.domain.exception.PedidoJaPagoException;
import br.com.tech.challenge.sistemapedido.domain.exception.PedidoNaoEncontradoException;
import br.com.tech.challenge.sistemapedido.usecase.gateway.PagamentoGateway;
import br.com.tech.challenge.sistemapedido.usecase.gateway.PedidoGateway;

import java.io.File;

public class GerarPagamentoPorQrCodeUseCase {
    private final PagamentoGateway pagamentoGateway;
    private final PedidoGateway pedidoGateway;

    public GerarPagamentoPorQrCodeUseCase(PagamentoGateway pagamentoGateway, PedidoGateway pedidoGateway) {
        this.pagamentoGateway = pagamentoGateway;
        this.pedidoGateway = pedidoGateway;
    }

    public File executar(Long idPedido) {
        var pedido = pedidoGateway.buscarPorId(idPedido)
                .orElseThrow(() -> new PedidoNaoEncontradoException(idPedido));

        if (pedido.estaPago()) {
            throw new PedidoJaPagoException(idPedido);
        }

        return pagamentoGateway.gerarPagamentoPorQrCode(pedido);
    }
}
