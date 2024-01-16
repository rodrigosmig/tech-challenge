package br.com.tech.challenge.sistemapedido.application.http.resource.v1;

import br.com.tech.challenge.sistemapedido.application.controller.FilaController;
import br.com.tech.challenge.sistemapedido.application.response.ListarPedidosResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pedidos/fila")
public class FilaResource {
    private final FilaController controller;

    @GetMapping("restaurante")
    public ResponseEntity<ListarPedidosResponse> filaRestaurante() {
        var resposta = controller.filaRestaurante();

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @GetMapping("cliente")
    public ResponseEntity<ListarPedidosResponse> filaCLiente() {
        var resposta = controller.filaRestaurante();

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }
}
