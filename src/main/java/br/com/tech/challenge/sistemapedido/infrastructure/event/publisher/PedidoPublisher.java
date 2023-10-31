package br.com.tech.challenge.sistemapedido.infrastructure.event.publisher;

import br.com.tech.challenge.sistemapedido.core.domain.event.PedidoPagoEvent;
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
}
