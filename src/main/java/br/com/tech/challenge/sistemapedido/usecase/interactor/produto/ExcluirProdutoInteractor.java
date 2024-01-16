package br.com.tech.challenge.sistemapedido.usecase.interactor.produto;

import br.com.tech.challenge.sistemapedido.core.repository.ProdutoRepository;
import br.com.tech.challenge.sistemapedido.usecase.contract.produto.BuscarProdutoUseCase;
import br.com.tech.challenge.sistemapedido.usecase.contract.produto.ExcluirProdutoUseCase;
import jakarta.inject.Named;

@Named
public class ExcluirProdutoInteractor implements ExcluirProdutoUseCase {
    private final ProdutoRepository repository;
    private final BuscarProdutoUseCase buscarProdutoUseCase;

    public ExcluirProdutoInteractor(ProdutoRepository repository, BuscarProdutoUseCase buscarProdutoUseCase) {
        this.repository = repository;
        this.buscarProdutoUseCase = buscarProdutoUseCase;
    }

    @Override
    public void excluir(Long id) {
        var produto = buscarProdutoUseCase.buscarPorId(id);
        repository.excluir(produto);
    }
}
