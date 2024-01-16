package br.com.tech.challenge.sistemapedido.usecase.interactor.pedido;

import br.com.tech.challenge.sistemapedido.domain.StatusPedido;
import br.com.tech.challenge.sistemapedido.domain.exception.PedidoNaoPagoException;
import br.com.tech.challenge.sistemapedido.domain.exception.PedidoStatusIncorretoException;
import br.com.tech.challenge.sistemapedido.usecase.gateway.PedidoGateway;
import br.com.tech.challenge.sistemapedido.usecase.contract.pedido.AlterarStatusPedidoUseCase;
import br.com.tech.challenge.sistemapedido.usecase.contract.pedido.BuscarPedidoUseCase;
import jakarta.inject.Named;

@Named
public class AlterarStatusPedidoInteractor implements AlterarStatusPedidoUseCase {
    private final PedidoGateway pedidoGateway;
    private final BuscarPedidoUseCase buscarPedidoUseCase;

    public AlterarStatusPedidoInteractor(PedidoGateway pedidoGateway, BuscarPedidoUseCase buscarPedidoUseCase) {
        this.pedidoGateway = pedidoGateway;
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

        pedidoGateway.alterarStatus(pedido);
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

        pedidoGateway.alterarStatus(pedido);
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

        pedidoGateway.alterarStatus(pedido);
    }
}
