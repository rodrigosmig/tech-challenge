package br.com.tech.challenge.sistemapedido.infrastructure.http.resource.v1.openapi;

import br.com.tech.challenge.sistemapedido.infrastructure.integration.rest.mercadopago.EventoConfirmacaoPagamento;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

@Tag(name = "Pagamentos", description = "Gerencia os pagamentos dos pedidos")
public interface PagamentoResourceOpenApi {

    @Operation(summary = "Gera o pagamento por QR-Code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "404",
                    description = "Pedido não encontrado",
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            value="{\"mensagem\": \"Pedido não encontrado\"}"
                                    )
                            }
                    )
            )
    })
    ResponseEntity<ByteArrayResource> gerarPagamento(Long id) throws IOException;


    @Operation(summary = "Recebe a confirmação de Pagamento do Mercado Livre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200")
    })
    ResponseEntity<Void> receberConfirmacaoPagamento(Long id, EventoConfirmacaoPagamento topic);
}
