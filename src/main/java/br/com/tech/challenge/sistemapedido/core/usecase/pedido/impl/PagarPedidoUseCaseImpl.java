package br.com.tech.challenge.sistemapedido.core.usecase.pedido.impl;

import br.com.tech.challenge.sistemapedido.core.exception.PedidoJaPagoException;
import br.com.tech.challenge.sistemapedido.core.repository.PedidoRepository;
import br.com.tech.challenge.sistemapedido.core.usecase.pedido.BuscarPedidoUseCase;
import br.com.tech.challenge.sistemapedido.core.usecase.pedido.PagarPedidoUseCase;
import jakarta.inject.Named;

@Named
public class PagarPedidoUseCaseImpl implements PagarPedidoUseCase {
    private final BuscarPedidoUseCase buscarPedidoUseCase;
    private final PedidoRepository pedidoRepository;

    public PagarPedidoUseCaseImpl(BuscarPedidoUseCase buscarPedidoUseCase, PedidoRepository pedidoRepository) {
        this.buscarPedidoUseCase = buscarPedidoUseCase;
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public void pagar(Long idPedido) {
        var pedido = buscarPedidoUseCase.buscarPorId(idPedido);

        if (Boolean.TRUE.equals(pedido.estaPago())) {
            throw new PedidoJaPagoException(pedido.getId());
        }

        pedidoRepository.pagar(pedido);
    }
}
