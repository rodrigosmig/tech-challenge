package br.com.tech.challenge.sistemapedido.core.domain.event;

import br.com.tech.challenge.sistemapedido.core.domain.Pedido;

public record PedidoPagoEvent(Pedido pedido) {}