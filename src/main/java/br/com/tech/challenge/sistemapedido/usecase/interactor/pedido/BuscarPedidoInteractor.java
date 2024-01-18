package br.com.tech.challenge.sistemapedido.usecase.interactor.pedido;

import br.com.tech.challenge.sistemapedido.domain.Pedido;
import br.com.tech.challenge.sistemapedido.domain.exception.PedidoNaoEncontradoException;
import br.com.tech.challenge.sistemapedido.usecase.gateway.PedidoGateway;
import br.com.tech.challenge.sistemapedido.usecase.contract.pedido.BuscarPedidoUseCase;
import jakarta.inject.Named;

import java.util.List;

@Named
public class BuscarPedidoInteractor implements BuscarPedidoUseCase {
    private final PedidoGateway repository;

    public BuscarPedidoInteractor(PedidoGateway repository) {
        this.repository = repository;
    }

    @Override
    public Pedido buscarPorId(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException(id));
    }

    @Override
    public List<Pedido> buscarTodos() {
        return repository.buscarTodos();
    }

    @Override
    public List<Pedido> buscarFilaRestaurante() {
        return repository.buscarFilaRestaurante();
    }

    @Override
    public List<Pedido> buscarFilaCliente() {
        return repository.buscarFilaCliente();
    }
}
