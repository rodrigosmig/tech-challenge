package br.com.tech.challenge.sistemapedido.usecase.pedido;

import br.com.tech.challenge.sistemapedido.domain.Pedido;
import br.com.tech.challenge.sistemapedido.domain.exception.PedidoNaoEncontradoException;
import br.com.tech.challenge.sistemapedido.usecase.gateway.PedidoGateway;
import jakarta.inject.Named;

import java.util.List;

@Named
public class BuscarPedidoUseCase {
    private final PedidoGateway repository;

    public BuscarPedidoUseCase(PedidoGateway repository) {
        this.repository = repository;
    }

    public Pedido buscarPorId(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException(id));
    }

    public List<Pedido> buscarTodos() {
        return repository.buscarTodos();
    }

    public List<Pedido> buscarFilaRestaurante() {
        return repository.buscarFilaRestaurante();
    }

    public List<Pedido> buscarFilaCliente() {
        return repository.buscarFilaCliente();
    }
}
