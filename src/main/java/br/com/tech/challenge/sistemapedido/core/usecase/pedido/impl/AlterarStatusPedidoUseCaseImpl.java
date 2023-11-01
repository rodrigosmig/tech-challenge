package br.com.tech.challenge.sistemapedido.core.usecase.pedido.impl;

import br.com.tech.challenge.sistemapedido.core.domain.Pedido;
import br.com.tech.challenge.sistemapedido.core.domain.StatusPedido;
import br.com.tech.challenge.sistemapedido.core.exception.PedidoNaoPagoException;
import br.com.tech.challenge.sistemapedido.core.exception.PedidoStatusIncorretoException;
import br.com.tech.challenge.sistemapedido.core.repository.PedidoRepository;
import br.com.tech.challenge.sistemapedido.core.usecase.pedido.AlterarStatusPedidoUseCase;
import br.com.tech.challenge.sistemapedido.core.usecase.pedido.BuscarPedidoUseCase;
import jakarta.inject.Named;

@Named
public class AlterarStatusPedidoUseCaseImpl implements AlterarStatusPedidoUseCase {
    private final PedidoRepository pedidoRepository;
    private final BuscarPedidoUseCase buscarPedidoUseCase;

    public AlterarStatusPedidoUseCaseImpl(PedidoRepository pedidoRepository, BuscarPedidoUseCase buscarPedidoUseCase) {
        this.pedidoRepository = pedidoRepository;
        this.buscarPedidoUseCase = buscarPedidoUseCase;
    }

    @Override
    public void alterarParaEmPreparacao(Long idPedido) {
        var pedido = buscarPedidoUseCase.buscarPorId(idPedido);

        if (Boolean.FALSE.equals(pedido.estaPago())) {
            throw new PedidoNaoPagoException(pedido.getId());
        }

        if (!pedido.getStatus().equals(StatusPedido.RECEBIDO)) {
            throw new PedidoStatusIncorretoException("Pedido não está com o status Recebido");
        }

        pedido.preparar();

        pedidoRepository.alterarStatus(pedido);
    }

    @Override
    public void alterarParaPronto(Long idPedido) {
        var pedido = buscarPedidoUseCase.buscarPorId(idPedido);

        if (Boolean.FALSE.equals(pedido.estaPago())) {
            throw new PedidoNaoPagoException(pedido.getId());
        }

        if (!pedido.getStatus().equals(StatusPedido.EM_PREPARACAO)) {
            throw new PedidoStatusIncorretoException("Pedido não está com o status Em Preparação");
        }

        pedido.pronto();

        pedidoRepository.alterarStatus(pedido);
    }

    @Override
    public void alterarParaFinalizado(Long idPedido) {
        var pedido = buscarPedidoUseCase.buscarPorId(idPedido);

        if (Boolean.FALSE.equals(pedido.estaPago())) {
            throw new PedidoNaoPagoException(pedido.getId());
        }

        if (!pedido.getStatus().equals(StatusPedido.PRONTO)) {
            throw new PedidoStatusIncorretoException("Pedido não está com o status Pronto");
        }

        pedido.finalizado();

        pedidoRepository.alterarStatus(pedido);
    }
}
