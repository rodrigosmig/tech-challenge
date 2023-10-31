package br.com.tech.challenge.sistemapedido.application.http.controller.v1.dto;

import br.com.tech.challenge.sistemapedido.core.domain.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoDTO(Long id,
                        StatusPedido status,
                        LocalDateTime dataCriacao,
                        LocalDateTime dataAtualizacao,
                        BigDecimal total,
                        Boolean pago,
                        List<ItemPedidoDTO> itens) {}
