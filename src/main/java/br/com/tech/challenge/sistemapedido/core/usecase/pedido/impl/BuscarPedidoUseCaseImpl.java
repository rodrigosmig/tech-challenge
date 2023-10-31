package br.com.tech.challenge.sistemapedido.core.usecase.pedido.impl;

import br.com.tech.challenge.sistemapedido.core.domain.Pedido;
import br.com.tech.challenge.sistemapedido.core.exception.PedidoNaoEncontradoException;
import br.com.tech.challenge.sistemapedido.core.repository.PedidoRepository;
import br.com.tech.challenge.sistemapedido.core.usecase.pedido.BuscarPedidoUseCase;
import jakarta.inject.Named;

import java.util.List;

@Named
public class BuscarPedidoUseCaseImpl implements BuscarPedidoUseCase {
    private final PedidoRepository repository;

    public BuscarPedidoUseCaseImpl(PedidoRepository repository) {
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
}
