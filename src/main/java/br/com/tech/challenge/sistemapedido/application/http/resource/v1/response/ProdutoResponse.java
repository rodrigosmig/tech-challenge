package br.com.tech.challenge.sistemapedido.application.http.resource.v1.response;

import br.com.tech.challenge.sistemapedido.application.http.resource.v1.dto.ProdutoDTO;
import lombok.Getter;

@Getter
public class ProdutoResponse {
    private ProdutoDTO produto;

    public ProdutoResponse(ProdutoDTO produto) {
        this.produto = produto;
    }
}
