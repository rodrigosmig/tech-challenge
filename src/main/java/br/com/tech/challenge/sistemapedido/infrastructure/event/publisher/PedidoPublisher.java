package br.com.tech.challenge.sistemapedido.infrastructure.event.publisher;

import br.com.tech.challenge.sistemapedido.domain.event.AlteracaoStatusPedidoEvent;
import br.com.tech.challenge.sistemapedido.domain.event.PedidoPagoEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PedidoPublisher {
    private final ApplicationEventPublisher eventPublisher;

    public void publishPedidoPagoEvent(PedidoPagoEvent event) {
        eventPublisher.publishEvent(event);
    }

    public void publishAteracaoStatusPedidoEvent(AlteracaoStatusPedidoEvent event) {
        eventPublisher.publishEvent(event);
    }
}
