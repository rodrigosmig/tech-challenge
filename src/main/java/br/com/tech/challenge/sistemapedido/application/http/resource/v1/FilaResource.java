package br.com.tech.challenge.sistemapedido.application.http.resource.v1;

import br.com.tech.challenge.sistemapedido.application.response.ListarPedidosResponse;
import br.com.tech.challenge.sistemapedido.application.mapper.PedidoDataMapper;
import br.com.tech.challenge.sistemapedido.usecase.contract.pedido.BuscarPedidoUseCase;
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
