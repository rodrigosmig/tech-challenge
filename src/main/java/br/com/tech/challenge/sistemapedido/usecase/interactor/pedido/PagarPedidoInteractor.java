package br.com.tech.challenge.sistemapedido.usecase.interactor.pedido;

import br.com.tech.challenge.sistemapedido.domain.exception.PedidoJaPagoException;
import br.com.tech.challenge.sistemapedido.usecase.gateway.PedidoGateway;
import br.com.tech.challenge.sistemapedido.usecase.contract.pedido.BuscarPedidoUseCase;
import br.com.tech.challenge.sistemapedido.usecase.contract.pedido.PagarPedidoUseCase;
import jakarta.inject.Named;

@Named
public class PagarPedidoInteractor implements PagarPedidoUseCase {
    private final BuscarPedidoUseCase buscarPedidoUseCase;
    private final PedidoGateway pedidoGateway;

    public PagarPedidoInteractor(BuscarPedidoUseCase buscarPedidoUseCase, PedidoGateway pedidoGateway) {
        this.buscarPedidoUseCase = buscarPedidoUseCase;
        this.pedidoGateway = pedidoGateway;
    }

    @Override
    public void pagar(Long idPedido) {
        var pedido = buscarPedidoUseCase.buscarPorId(idPedido);

        if (Boolean.TRUE.equals(pedido.estaPago())) {
            throw new PedidoJaPagoException(pedido.getId());
        }

        pedidoGateway.pagar(pedido);
    }

    @Override
    public void gerarPagamento(Long idPedido) {
        var pedido = buscarPedidoUseCase.buscarPorId(idPedido);

        if (pedido.estaPago()) {
            throw new PedidoJaPagoException(idPedido);
        }

        var teste = "";
    }
}
