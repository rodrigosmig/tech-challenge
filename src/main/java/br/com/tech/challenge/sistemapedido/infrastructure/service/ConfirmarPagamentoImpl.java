package br.com.tech.challenge.sistemapedido.infrastructure.service;

import br.com.tech.challenge.sistemapedido.infrastructure.integration.rest.mercadopago.MercadoPagoHttpClient;
import br.com.tech.challenge.sistemapedido.usecase.contract.pedido.PagarPedidoUseCase;
import br.com.tech.challenge.sistemapedido.usecase.service.ConfirmarPagamento;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ConfirmarPagamentoImpl implements ConfirmarPagamento {
    private final MercadoPagoHttpClient mercadoPagoHttpClient;
    private final PagarPedidoUseCase pagarPedidoUseCase;

    @Override
    public void confirmarPagamento(Long idExterno) {
        var response = mercadoPagoHttpClient.consultarMerchantOrder(idExterno);
        var consultaPagamentoResponse = response.getBody();

        if (Objects.nonNull(consultaPagamentoResponse) && consultaPagamentoResponse.isPaid()) {
            pagarPedidoUseCase.pagar(consultaPagamentoResponse.externalReference());
        }
    }
}
