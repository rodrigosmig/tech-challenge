package br.com.tech.challenge.sistemapedido.application.http.controller.v1.response;

import lombok.Getter;

@Getter
public class CadastrarPedidoResponse {
    private Long idPedido;

    public CadastrarPedidoResponse(Long idPedido) {
        this.idPedido = idPedido;
    }
}
