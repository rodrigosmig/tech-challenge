package br.com.tech.challenge.sistemapedido.application.controller;

import br.com.tech.challenge.sistemapedido.application.mapper.PedidoDataMapper;
import br.com.tech.challenge.sistemapedido.application.response.ListarPedidosResponse;
import br.com.tech.challenge.sistemapedido.usecase.contract.pedido.BuscarPedidoUseCase;
import jakarta.inject.Named;

@Named
public class FilaController {
    private final BuscarPedidoUseCase buscarPedidoUseCase;
    private final PedidoDataMapper pedidoMapper;

    public FilaController(BuscarPedidoUseCase buscarPedidoUseCase, PedidoDataMapper pedidoMapper) {
        this.buscarPedidoUseCase = buscarPedidoUseCase;
        this.pedidoMapper = pedidoMapper;
    }

    public ListarPedidosResponse filaRestaurante() {
        var pedidos = buscarPedidoUseCase.buscarFilaRestaurante();

        return new ListarPedidosResponse(pedidoMapper.toList(pedidos));
    }

    public ListarPedidosResponse filaCLiente() {
        var pedidos = buscarPedidoUseCase.buscarFilaCliente();

        return new ListarPedidosResponse(pedidoMapper.toList(pedidos));
    }
}
