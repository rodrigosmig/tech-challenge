package br.com.tech.challenge.sistemapedido.infrastructure.repository;

import br.com.tech.challenge.sistemapedido.core.domain.ItemPedido;
import br.com.tech.challenge.sistemapedido.core.domain.Pedido;
import br.com.tech.challenge.sistemapedido.core.domain.StatusPedido;
import br.com.tech.challenge.sistemapedido.core.domain.event.AlteracaoStatusPedidoEvent;
import br.com.tech.challenge.sistemapedido.core.domain.event.PedidoPagoEvent;
import br.com.tech.challenge.sistemapedido.usecase.repository.PedidoGateway;
import br.com.tech.challenge.sistemapedido.infrastructure.event.publisher.PedidoPublisher;
import br.com.tech.challenge.sistemapedido.infrastructure.mapper.ItemPedidoModelMapper;
import br.com.tech.challenge.sistemapedido.infrastructure.mapper.PedidoModelMapper;
import br.com.tech.challenge.sistemapedido.infrastructure.model.FilaClienteModel;
import br.com.tech.challenge.sistemapedido.infrastructure.model.FilaRestauranteModel;
import br.com.tech.challenge.sistemapedido.infrastructure.repository.jpa.FilaClienteRepositoryJpa;
import br.com.tech.challenge.sistemapedido.infrastructure.repository.jpa.FilaRestauranteRepositoryJpa;
import br.com.tech.challenge.sistemapedido.infrastructure.repository.jpa.ItemPedidoRepositoryJpa;
import br.com.tech.challenge.sistemapedido.infrastructure.repository.jpa.PedidoRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PedidoGatewayImpl implements PedidoGateway {
    private final PedidoRepositoryJpa pedidoRepository;
    private final ItemPedidoRepositoryJpa itemPedidoRepository;
    private final FilaRestauranteRepositoryJpa filaRestauranteRepository;
    private final FilaClienteRepositoryJpa filaClienteRepositoryJpa;
    private final PedidoPublisher pedidoPublisher;

    private final PedidoModelMapper pedidoMapper;
    private final ItemPedidoModelMapper itemPedidoMapper;

    @Override
    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .map(pedidoMapper::toDomain);
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

    @Override
    public void pagar(Pedido pedido) {
        pedido.pagar();

        pedidoRepository.save(pedidoMapper.toModel(pedido));

        pedidoPublisher.publishPedidoPagoEvent(new PedidoPagoEvent(pedido));
    }

    @Override
    public void alterarStatus(Pedido pedido) {
        pedidoRepository.save(pedidoMapper.toModel(pedido));

        pedidoPublisher.publishAteracaoStatusPedidoEvent(new AlteracaoStatusPedidoEvent(pedido));
    }

    @Override
    public List<Pedido> buscarFilaRestaurante() {
        var fila = filaRestauranteRepository.findAllByPedidoStatusIn(new LinkedList<>(Arrays.asList(StatusPedido.RECEBIDO, StatusPedido.EM_PREPARACAO, StatusPedido.PRONTO)));

        return fila.stream()
                .map(FilaRestauranteModel::getPedido)
                .map(pedidoMapper::toDomain)
                .toList();
    }

    @Override
    public List<Pedido> buscarFilaCliente() {
        var statusArray = new StatusPedido[]{StatusPedido.EM_PREPARACAO, StatusPedido.PRONTO};
        var fila = filaClienteRepositoryJpa.findAllByPedidoStatusIn(new LinkedList<>(Arrays.asList(statusArray)));

        return fila.stream()
                .map(FilaClienteModel::getPedido)
                .map(pedidoMapper::toDomain)
                .toList();
    }
}
