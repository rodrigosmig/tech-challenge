package br.com.tech.challenge.sistemapedido.core.repository;

import br.com.tech.challenge.sistemapedido.core.domain.ItemPedido;
import br.com.tech.challenge.sistemapedido.core.domain.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository {
    Optional<Pedido> buscarPorId(Long id);
    List<Pedido> buscarTodos();
    Pedido salvar(Pedido pedido, List<ItemPedido> itemPedidos);
}
