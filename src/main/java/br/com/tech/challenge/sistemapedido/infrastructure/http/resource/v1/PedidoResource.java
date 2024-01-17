package br.com.tech.challenge.sistemapedido.infrastructure.http.resource.v1;

import br.com.tech.challenge.sistemapedido.application.controller.PedidoController;
import br.com.tech.challenge.sistemapedido.application.request.PedidoRequest;
import br.com.tech.challenge.sistemapedido.application.response.CadastrarPedidoResponse;
import br.com.tech.challenge.sistemapedido.application.response.ListarPedidosResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pedidos")
public class PedidoResource {
    //adicionar o swagger
    private final PedidoController controller;

    @PostMapping
    public ResponseEntity<CadastrarPedidoResponse> criar(@RequestBody PedidoRequest request) {
        var resposta = controller.criar(request);

        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ListarPedidosResponse> listar() {
        var resposta = controller.listar();

        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    @PostMapping("{idPedido}/pagar")
    public ResponseEntity<Void> pagar(@PathVariable Long idPedido) {
        controller.pagar(idPedido);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("preparacao/{idPedido}")
    public ResponseEntity<Void> preparacao(@PathVariable Long idPedido) {
        controller.preparacao(idPedido);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("pronto/{idPedido}")
    public ResponseEntity<Void> pronto(@PathVariable Long idPedido) {
        controller.pronto(idPedido);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("finalizado/{idPedido}")
    public ResponseEntity<Void> finalizado(@PathVariable Long idPedido) {
        controller.finalizado(idPedido);

        return ResponseEntity.noContent().build();
    }
}
