package br.com.tech.challenge.sistemapedido.infrastructure.mapper;

import br.com.tech.challenge.sistemapedido.domain.Pedido;
import br.com.tech.challenge.sistemapedido.domain.vo.Data;
import br.com.tech.challenge.sistemapedido.domain.vo.Preco;
import br.com.tech.challenge.sistemapedido.infrastructure.persistence.model.PedidoModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PedidoModelMapper {
    private final ItemPedidoModelMapper itemPedidoMapper;

    public Pedido toDomain(PedidoModel pedido) {
        var novoPedido = Pedido.builder()
                .id(pedido.getId())
                .status(pedido.getStatus())
                .dataCriacao(new Data(pedido.getDataCriacao()))
                .dataAtualizacao(new Data(pedido.getDataAtualizacao()))
                .total(new Preco(pedido.getTotal()))
                .pago(pedido.getPago())
                .build();

        var itens = pedido.getItens().stream()
                .map(itemPedidoMapper::toDomain)
                .map(itemPedido -> {
                    itemPedido.adicionarPedido(novoPedido);
                    return itemPedido;
                })
                .toList();

        novoPedido.adicionarItens(itens);

        return novoPedido;
    }

    public PedidoModel toModel(Pedido pedido) {
        var novoPedido = PedidoModel.builder()
                .id(pedido.getId())
                .status(pedido.getStatus())
                .dataCriacao(pedido.getDataCriacao().getData())
                .dataAtualizacao(pedido.getDataAtualizacao().getData())
                .total(pedido.getTotal().getPreco())
                .pago(pedido.estaPago())
                .build();

        var itens = pedido.getItens().stream()
                .map(itemPedidoMapper::toModel)
                .toList();

        novoPedido.setItens(itens);

        return novoPedido;
    }
}
