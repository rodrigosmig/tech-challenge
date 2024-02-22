package br.com.tech.challenge.sistemapedido.usecase.produto;

import br.com.tech.challenge.sistemapedido.usecase.gateway.ProdutoGateway;
import br.com.tech.challenge.sistemapedido.usecase.contract.produto.BuscarProdutoUseCase;
import jakarta.inject.Named;

@Named
public class ExcluirProdutoUseCase {
    private final ProdutoGateway repository;
    private final BuscarProdutoUseCase buscarProdutoUseCase;

    public ExcluirProdutoUseCase(ProdutoGateway repository, BuscarProdutoUseCase buscarProdutoUseCase) {
        this.repository = repository;
        this.buscarProdutoUseCase = buscarProdutoUseCase;
    }

    public void excluir(Long id) {
        var produto = buscarProdutoUseCase.buscarPorId(id);
        repository.excluir(produto);
    }
}
