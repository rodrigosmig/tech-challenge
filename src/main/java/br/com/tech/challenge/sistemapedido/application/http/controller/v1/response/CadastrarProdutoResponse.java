package br.com.tech.challenge.sistemapedido.application.http.controller.v1.response;

import lombok.Getter;

@Getter
public class CadastrarProdutoResponse {
    private Long idProduto;

    public CadastrarProdutoResponse(Long idProduto) {
        this.idProduto = idProduto;
    }
}
