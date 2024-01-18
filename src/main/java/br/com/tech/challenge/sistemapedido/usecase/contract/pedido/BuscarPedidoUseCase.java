package br.com.tech.challenge.sistemapedido.usecase.contract.pedido;

import br.com.tech.challenge.sistemapedido.domain.Pedido;

import java.util.List;

public interface BuscarPedidoUseCase {
    Pedido buscarPorId(Long id);
    List<Pedido> buscarTodos();
    List<Pedido> buscarFilaRestaurante();
    List<Pedido> buscarFilaCliente();
}
