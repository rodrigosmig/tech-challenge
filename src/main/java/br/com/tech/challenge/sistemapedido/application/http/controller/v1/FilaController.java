package br.com.tech.challenge.sistemapedido.application.http.controller.v1;

import br.com.tech.challenge.sistemapedido.application.http.controller.v1.response.ListarPedidosResponse;
import br.com.tech.challenge.sistemapedido.application.http.mapper.PedidoDataMapper;
import br.com.tech.challenge.sistemapedido.core.usecase.pedido.BuscarPedidoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/pedidos/fila")
public class FilaController {
    private final BuscarPedidoUseCase buscarPedidoUseCase;
    private final PedidoDataMapper pedidoMapper;

    @GetMapping("restaurante")
    public ResponseEntity<ListarPedidosResponse> filaRestaurante() {
        var pedidos = buscarPedidoUseCase.buscarFilaRestaurante();
        var resposta = new ListarPedidosResponse(pedidoMapper.toList(pedidos));

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @GetMapping("cliente")
    public ResponseEntity<ListarPedidosResponse> filaCLiente() {
        var pedidos = buscarPedidoUseCase.buscarFilaCliente();
        var resposta = new ListarPedidosResponse(pedidoMapper.toList(pedidos));

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }
}