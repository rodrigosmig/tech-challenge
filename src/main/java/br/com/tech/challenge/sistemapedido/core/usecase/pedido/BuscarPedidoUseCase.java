package br.com.tech.challenge.sistemapedido.core.usecase.pedido;

import br.com.tech.challenge.sistemapedido.core.domain.Pedido;

import java.util.List;

public interface BuscarPedidoUseCase {
    Pedido buscarPorId(Long id);
    List<Pedido> buscarTodos();
    List<Pedido> buscarFilaRestaurante();
}
