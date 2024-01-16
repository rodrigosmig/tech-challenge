package br.com.tech.challenge.sistemapedido.usecase.contract.pedido;

import br.com.tech.challenge.sistemapedido.domain.ItemPedido;
import br.com.tech.challenge.sistemapedido.domain.Pedido;

import java.util.List;

public interface CriarPedidoUseCase {
    Pedido criar(List<ItemPedido> pedido);

}
