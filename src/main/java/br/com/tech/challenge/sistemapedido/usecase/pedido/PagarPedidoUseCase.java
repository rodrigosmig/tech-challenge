package br.com.tech.challenge.sistemapedido.usecase.pedido;

import br.com.tech.challenge.sistemapedido.domain.exception.PedidoJaPagoException;
import br.com.tech.challenge.sistemapedido.usecase.gateway.PedidoGateway;
import br.com.tech.challenge.sistemapedido.usecase.contract.pedido.BuscarPedidoUseCase;
import br.com.tech.challenge.sistemapedido.usecase.service.GerarPagamentoService;
import jakarta.inject.Named;

import java.io.File;

@Named
public class PagarPedidoUseCase {
    private final BuscarPedidoUseCase buscarPedidoUseCase;
    private final GerarPagamentoService gerarPagamentoService;
    private final PedidoGateway pedidoGateway;

    public PagarPedidoUseCase(BuscarPedidoUseCase buscarPedidoUseCase, GerarPagamentoService gerarPagamentoService, PedidoGateway pedidoGateway) {
        this.buscarPedidoUseCase = buscarPedidoUseCase;
        this.gerarPagamentoService = gerarPagamentoService;
        this.pedidoGateway = pedidoGateway;
    }

    public void pagar(Long idPedido) {
        var pedido = buscarPedidoUseCase.buscarPorId(idPedido);

        if (Boolean.TRUE.equals(pedido.estaPago())) {
            throw new PedidoJaPagoException(pedido.getId());
        }

        pedido.pagar();

        pedidoGateway.pagar(pedido);
    }

    public File gerarPagamento(Long idPedido) {
        var pedido = buscarPedidoUseCase.buscarPorId(idPedido);

        if (pedido.estaPago()) {
            throw new PedidoJaPagoException(idPedido);
        }

        return gerarPagamentoService.gerarQrCode(pedido);
    }
}
