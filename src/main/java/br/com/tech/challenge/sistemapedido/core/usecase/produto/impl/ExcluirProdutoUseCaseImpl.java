package br.com.tech.challenge.sistemapedido.core.usecase.produto.impl;

import br.com.tech.challenge.sistemapedido.core.repository.ProdutoRepository;
import br.com.tech.challenge.sistemapedido.core.usecase.produto.BuscarProdutoUseCase;
import br.com.tech.challenge.sistemapedido.core.usecase.produto.ExcluirProdutoUseCase;
import jakarta.inject.Named;

@Named
public class ExcluirProdutoUseCaseImpl implements ExcluirProdutoUseCase {
    private final ProdutoRepository repository;
    private final BuscarProdutoUseCase buscarProdutoUseCase;

    public ExcluirProdutoUseCaseImpl(ProdutoRepository repository, BuscarProdutoUseCase buscarProdutoUseCase) {
        this.repository = repository;
        this.buscarProdutoUseCase = buscarProdutoUseCase;
    }

    @Override
    public void excluir(Long id) {
        var produto = buscarProdutoUseCase.buscarPorId(id);
        repository.excluir(produto);
    }
}
