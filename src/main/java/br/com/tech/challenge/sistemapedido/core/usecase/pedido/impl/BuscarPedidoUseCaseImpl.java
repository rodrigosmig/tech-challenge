package br.com.tech.challenge.sistemapedido.core.usecase.pedido.impl;

import br.com.tech.challenge.sistemapedido.core.domain.Pedido;
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
        return null;
    }

    @Override
    public List<Pedido> buscarTodos() {
        return repository.buscarTodos();
    }
}
