package br.com.tech.challenge.sistemapedido.application.gateway;

import br.com.tech.challenge.sistemapedido.application.repository.FilaRepository;
import br.com.tech.challenge.sistemapedido.application.repository.PedidoRepository;
import br.com.tech.challenge.sistemapedido.domain.Pedido;
import br.com.tech.challenge.sistemapedido.domain.event.AlteracaoStatusPedidoEvent;
import br.com.tech.challenge.sistemapedido.domain.event.PedidoPagoEvent;
import br.com.tech.challenge.sistemapedido.infrastructure.event.publisher.PedidoPublisher;
import br.com.tech.challenge.sistemapedido.usecase.gateway.PedidoGateway;
import jakarta.inject.Named;

import java.util.List;
import java.util.Optional;

@Named
//TODO gateway vai ficar na camada de aplicação e não pode conhecer a camada de infra
public class PedidoGatewayImpl implements PedidoGateway {
    private final PedidoRepository pedidoRepository;
    private final FilaRepository filaRepository;
    private final PedidoPublisher pedidoPublisher;

    public PedidoGatewayImpl(PedidoRepository pedidoRepository, FilaRepository filaRepository, PedidoPublisher pedidoPublisher) {
        this.pedidoRepository = pedidoRepository;
        this.filaRepository = filaRepository;
        this.pedidoPublisher = pedidoPublisher;
    }

    @Override
    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public List<Pedido> buscarTodos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido salvar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public void pagar(Pedido pedido) {
        pedidoRepository.save(pedido);
        //TODO alterar publicador de evento
        pedidoPublisher.publishPedidoPagoEvent(new PedidoPagoEvent(pedido));
    }

    @Override
    public void alterarStatus(Pedido pedido) {
        pedidoRepository.save(pedido);

        //TODO alterar publicador de evento
        pedidoPublisher.publishAteracaoStatusPedidoEvent(new AlteracaoStatusPedidoEvent(pedido));
    }

    @Override
    public List<Pedido> buscarFilaRestaurante() {
        return filaRepository.buscarFilaRestaurante();
    }

    @Override
    public List<Pedido> buscarFilaCliente() {
        return filaRepository.buscarFilaCliente();
    }
}
