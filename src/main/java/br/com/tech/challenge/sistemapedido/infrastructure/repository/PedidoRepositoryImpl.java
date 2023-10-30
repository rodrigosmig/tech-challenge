package br.com.tech.challenge.sistemapedido.infrastructure.repository;

import br.com.tech.challenge.sistemapedido.core.domain.ItemPedido;
import br.com.tech.challenge.sistemapedido.core.domain.Pedido;
import br.com.tech.challenge.sistemapedido.core.repository.PedidoRepository;
import br.com.tech.challenge.sistemapedido.infrastructure.mapper.ItemPedidoModelMapper;
import br.com.tech.challenge.sistemapedido.infrastructure.mapper.PedidoModelMapper;
import br.com.tech.challenge.sistemapedido.infrastructure.repository.jpa.ItemPedidoRepositoryJpa;
import br.com.tech.challenge.sistemapedido.infrastructure.repository.jpa.PedidoRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PedidoRepositoryImpl implements PedidoRepository {
    private final PedidoRepositoryJpa pedidoRepository;
    private final ItemPedidoRepositoryJpa itemPedidoRepository;
    private final PedidoModelMapper pedidoMapper;
    private final ItemPedidoModelMapper itemPedidoMapper;

    @Override
    public Optional<Pedido> buscarPorId(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Pedido> buscarTodos() {
        return pedidoRepository.findAll()
                .stream()
                .map(pedidoMapper::toDomain)
                .toList();
    }

    @Override
    public Pedido salvar(Pedido pedido, List<ItemPedido> itemPedidos) {
        var itensModel = itemPedidos.stream()
                .map(itemPedidoMapper::toModel)
                .map(item -> {
                    item.setPedido(pedidoMapper.toModel(pedido));
                    return item;
                })
                .toList();

        itensModel = itemPedidoRepository.saveAll(itensModel);
        var pedidoModel = pedidoMapper.toModel(pedido);
        pedidoModel.setItens(itensModel);
        pedidoModel = pedidoRepository.save(pedidoModel);

        return pedidoMapper.toDomain(pedidoModel);
    }
}
