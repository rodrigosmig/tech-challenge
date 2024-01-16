package br.com.tech.challenge.sistemapedido.application.http.mapper;

import br.com.tech.challenge.sistemapedido.application.http.controller.v1.dto.PedidoDTO;
import br.com.tech.challenge.sistemapedido.domain.Pedido;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Named
@RequiredArgsConstructor
public class PedidoDataMapper {
    private final ItemPedidoDataMapper itemPedidoMapper;
    public PedidoDTO toDTO(Pedido pedido) {
        var itens = pedido.getItens()
                .stream()
                .map(itemPedidoMapper::toDTO)
                .toList();

        return new PedidoDTO(pedido.getId(),
                pedido.getStatus(),
                pedido.getDataCriacao().getData(),
                pedido.getDataAtualizacao().getData(),
                pedido.getTotal().getPreco(),
                pedido.estaPago(),
                itens);
    }

    public List<PedidoDTO> toList(List<Pedido> pedidos) {
        return pedidos
                .stream()
                .map(this::toDTO)
                .toList();
    }
}
