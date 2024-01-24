package br.com.tech.challenge.sistemapedido.infrastructure.integration.rest.mercadopago;

import br.com.tech.challenge.sistemapedido.infrastructure.integration.transfer.ItemTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class GerarCodigoQrRequest {
    @JsonProperty("external_reference")
    private String externalReference;

    private String description;

    @JsonProperty("expiration_date")
    private ZonedDateTime expirationDate;

    @JsonProperty("notification_url")
    private String notificationUrl;

    private String title;

    @JsonProperty("total_amount")
    private BigDecimal totalAmount;

    private List<ItemTO> items;
}