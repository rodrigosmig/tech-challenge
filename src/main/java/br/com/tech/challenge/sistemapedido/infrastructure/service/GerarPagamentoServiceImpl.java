package br.com.tech.challenge.sistemapedido.infrastructure.service;

import br.com.tech.challenge.sistemapedido.domain.ItemPedido;
import br.com.tech.challenge.sistemapedido.domain.Pedido;
import br.com.tech.challenge.sistemapedido.domain.exception.SistemaPedidosAPIException;
import br.com.tech.challenge.sistemapedido.infrastructure.integration.rest.mercadopago.GerarCodigoQrRequest;
import br.com.tech.challenge.sistemapedido.infrastructure.integration.rest.mercadopago.MercadoPagoHttpClient;
import br.com.tech.challenge.sistemapedido.infrastructure.integration.transfer.ItemTO;
import br.com.tech.challenge.sistemapedido.usecase.service.GerarPagamentoService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GerarPagamentoServiceImpl implements GerarPagamentoService {
    private final MercadoPagoHttpClient mercadopagoHttpClient;
    private final Long HORAS = 2L;

    @Override
    public void gerar(Pedido pedido) {
        var request = obterRequest(pedido);
        try {
            var gerarCodigoResponse = mercadopagoHttpClient.gerarCodigoQR(request);
            var response = gerarCodigoResponse.getBody();
        } catch (FeignException ex) {
            //TODO melhora a captura da excecao
          throw new SistemaPedidosAPIException(HttpStatus.valueOf(ex.status()), ex.getMessage());
        }

    }

    private GerarCodigoQrRequest obterRequest(Pedido pedido) {
        var items = this.obterItems(pedido.getItens());

        ZoneId zone = ZoneId.of("America/Sao_Paulo");
        ZonedDateTime expiration = new Date().toInstant()
                .atZone(zone)
                .toLocalDateTime().plusHours(HORAS).atZone(zone);

        return GerarCodigoQrRequest.builder()
                .description("Pedido para pagamento")
                .externalReference(pedido.getId().toString())
                .expirationDate(expiration)
                .notificationUrl("https://www.yourserver.com/notifications")
                .title("Restaurante Fiap")
                .totalAmount(pedido.getTotal().getPreco())
                .items(items)
                .build();
    }

    private List<ItemTO> obterItems(List<ItemPedido> itemsPedido) {
        return itemsPedido.stream()
                .map(itemPedido -> ItemTO.builder()
                        .title(itemPedido.getProduto().getNome().toString())
                        .description(itemPedido.getProduto().getDescricao().toString())
                        .unitPrice(itemPedido.getProduto().getPreco().getPreco())
                        .quantity(itemPedido.getQuantidade().getQuantidade())
                        .unitMeasure("unit")
                        .totalAmount(itemPedido.getProduto().getPreco().getPreco().multiply(new BigDecimal(itemPedido.getQuantidade().getQuantidade())))
                        .build())
                .toList();
    }
}
