package br.com.tech.challenge.sistemapedido.infrastructure.integration.rest.mercadopago;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GerarCodigoQrResponse {
    @JsonProperty("in_store_order_id")
    private final String inStoreOrderid;

    @JsonProperty("qr_data")
    private final String qrData;
}
