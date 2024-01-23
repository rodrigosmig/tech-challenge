package br.com.tech.challenge.sistemapedido.infrastructure.integration.rest.mercadopago;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "mercadopago", url = "${rest.service.mercadopago.url}")
public interface MercadoPagoHttpClient {
    @GetMapping("/instore/orders/qr/seller/collectors/${rest.service.mercadopago.user-id}/pos/${rest.service.mercadopago.pos-id}/qrs")
    ResponseEntity<GerarCodigoQrResponse> gerarCodigoQR(GerarCodigoQrRequest request);
}
