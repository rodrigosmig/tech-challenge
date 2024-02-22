package br.com.tech.challenge.sistemapedido.usecase.produto;

import br.com.tech.challenge.sistemapedido.usecase.gateway.ProdutoGateway;
import jakarta.inject.Named;

@Named
public class ExcluirProdutoUseCase {
    private final ProdutoGateway produtoGateway;

    public ExcluirProdutoUseCase(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public void excluir(Long id) {
        var buscarProdutoUseCase = new BuscarProdutoUseCase(this.produtoGateway);
        var produto = buscarProdutoUseCase.buscarPorId(id);
        produtoGateway.excluir(produto);
    }
}
