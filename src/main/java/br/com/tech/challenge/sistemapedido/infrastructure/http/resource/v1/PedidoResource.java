package br.com.tech.challenge.sistemapedido.infrastructure.http.resource.v1;

import br.com.tech.challenge.sistemapedido.application.controller.PedidoController;
import br.com.tech.challenge.sistemapedido.application.request.PedidoRequest;
import br.com.tech.challenge.sistemapedido.application.response.CadastrarPedidoResponse;
import br.com.tech.challenge.sistemapedido.application.response.ListarPedidosResponse;
import br.com.tech.challenge.sistemapedido.application.response.StatusPedidoResponse;
import br.com.tech.challenge.sistemapedido.infrastructure.http.resource.v1.openapi.PedidoResourceOpenApi;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pedidos")
public class PedidoResource implements PedidoResourceOpenApi {
    private final PedidoController controller;

    @Override
    @PostMapping
    public ResponseEntity<CadastrarPedidoResponse> criar(@RequestBody PedidoRequest request) {
        var resposta = controller.criar(request);

        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    @Override
    @GetMapping
    public ResponseEntity<ListarPedidosResponse> listar() {
        var resposta = controller.listar();

        return ResponseEntity.ok(resposta);
    }

    @Override
    @PatchMapping("preparacao/{idPedido}")
    public ResponseEntity<Void> preparacao(@PathVariable Long idPedido) {
        controller.preparacao(idPedido);

        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping("pronto/{idPedido}")
    public ResponseEntity<Void> pronto(@PathVariable Long idPedido) {
        controller.pronto(idPedido);

        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping("finalizado/{idPedido}")
    public ResponseEntity<Void> finalizado(@PathVariable Long idPedido) {
        controller.finalizado(idPedido);

        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("{idPedido}/status")
    public ResponseEntity<StatusPedidoResponse> verificarStatus(@PathVariable Long idPedido) {
        var resposta = controller.verificarStatus(idPedido);

        return ResponseEntity.ok(resposta);
    }

    @Override
    @PostMapping("/{idPedido}/gerar-pagamento")
    public ResponseEntity<ByteArrayResource> gerarPagamento(@PathVariable Long idPedido) throws IOException {
        var arquivo = controller.gerarPagamento(idPedido);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=mercadopago.png");
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(arquivo.length())
                .contentType(MediaType.IMAGE_PNG)
                .body(new ByteArrayResource(Files.readAllBytes(arquivo.toPath())));
    }

    @Override
    @PostMapping("/confirmar-pagamento")
    public ResponseEntity<Void> receberConfirmacaoPagamento(@RequestParam(required = false) Long id,
                                                      @RequestParam(required = false) String topic) {

        if (Objects.nonNull(topic) && topic.equalsIgnoreCase("merchant_order") && Objects.nonNull(id)) {
            controller.receberConfirmacaoPagamento(id);
        }

        return ResponseEntity.ok().build();
    }
}
