package br.com.tech.challenge.sistemapedido.application.http.resource.v1.response;

import br.com.tech.challenge.sistemapedido.application.http.resource.v1.dto.ProdutoDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class ListarProdutosResponse {
    private List<ProdutoDTO> produtos;

    public ListarProdutosResponse(List<ProdutoDTO> produtos) {
        this.produtos = produtos;
    }
}
