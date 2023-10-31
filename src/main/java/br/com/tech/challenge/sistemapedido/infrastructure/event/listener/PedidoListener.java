package br.com.tech.challenge.sistemapedido.infrastructure.event.listener;

import br.com.tech.challenge.sistemapedido.application.http.mapper.PedidoDataMapper;
import br.com.tech.challenge.sistemapedido.core.domain.event.PedidoPagoEvent;
import br.com.tech.challenge.sistemapedido.core.usecase.pedido.AlterarStatusPedidoUseCase;
import br.com.tech.challenge.sistemapedido.infrastructure.mapper.PedidoModelMapper;
import br.com.tech.challenge.sistemapedido.infrastructure.model.FilaRestauranteModel;
import br.com.tech.challenge.sistemapedido.infrastructure.repository.jpa.FilaRestauranteRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PedidoListener {
    private final AlterarStatusPedidoUseCase alterarStatusPedido;
    private final FilaRestauranteRepositoryJpa filaRestauranteRepository;
    private final PedidoModelMapper pedidoDataMapper;

    @EventListener
    public void adicionarNaFilaRestaurante(PedidoPagoEvent event) {
        var fila = FilaRestauranteModel.builder()
                .pedido(pedidoDataMapper.toModel(event.pedido()))
                .dataAtualizacao(LocalDateTime.now())
                .build();

        filaRestauranteRepository.save(fila);
    }
}
