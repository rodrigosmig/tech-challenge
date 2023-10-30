package br.com.tech.challenge.sistemapedido.core.usecase.pedido;

import br.com.tech.challenge.sistemapedido.core.domain.ItemPedido;
import br.com.tech.challenge.sistemapedido.core.domain.Pedido;

import java.util.List;

public interface CriarPedidoUseCase {
    Pedido criar(List<ItemPedido> pedido);

}
