package br.com.tech.challenge.sistemapedido.usecase.interactor.produto;

import br.com.tech.challenge.sistemapedido.usecase.gateway.ProdutoGateway;
import br.com.tech.challenge.sistemapedido.usecase.contract.produto.BuscarProdutoUseCase;
import br.com.tech.challenge.sistemapedido.usecase.contract.produto.ExcluirProdutoUseCase;
import jakarta.inject.Named;

@Named
public class ExcluirProdutoInteractor implements ExcluirProdutoUseCase {
    private final ProdutoGateway repository;
    private final BuscarProdutoUseCase buscarProdutoUseCase;

    public ExcluirProdutoInteractor(ProdutoGateway repository, BuscarProdutoUseCase buscarProdutoUseCase) {
        this.repository = repository;
        this.buscarProdutoUseCase = buscarProdutoUseCase;
    }

    @Override
    public void excluir(Long id) {
        var produto = buscarProdutoUseCase.buscarPorId(id);
        repository.excluir(produto);
    }
}
