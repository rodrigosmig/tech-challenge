package br.com.tech.challenge.sistemapedido.application.response;

import br.com.tech.challenge.sistemapedido.application.dto.ProdutoDTO;
import lombok.Getter;

@Getter
public class ProdutoResponse {
    private final ProdutoDTO produto;

    public ProdutoResponse(ProdutoDTO produto) {
        this.produto = produto;
    }
}
