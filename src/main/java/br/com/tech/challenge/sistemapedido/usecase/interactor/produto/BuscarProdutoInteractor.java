package br.com.tech.challenge.sistemapedido.usecase.interactor.produto;

import br.com.tech.challenge.sistemapedido.core.domain.Produto;
import br.com.tech.challenge.sistemapedido.core.exception.ProdutoNaoEncontradoException;
import br.com.tech.challenge.sistemapedido.usecase.repository.ProdutoGateway;
import br.com.tech.challenge.sistemapedido.usecase.contract.produto.BuscarProdutoUseCase;
import jakarta.inject.Named;

import java.util.List;

@Named
public class BuscarProdutoInteractor implements BuscarProdutoUseCase {
    private final ProdutoGateway repository;

    public BuscarProdutoInteractor(ProdutoGateway repository) {
        this.repository = repository;
    }

    @Override
    public Produto buscarPorId(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }

    @Override
    public List<Produto> buscarTodos() {
        return repository.buscarTodos();
    }
}
