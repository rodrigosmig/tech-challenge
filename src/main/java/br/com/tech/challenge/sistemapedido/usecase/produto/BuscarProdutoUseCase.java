package br.com.tech.challenge.sistemapedido.usecase.produto;

import br.com.tech.challenge.sistemapedido.domain.Produto;
import br.com.tech.challenge.sistemapedido.domain.exception.ProdutoNaoEncontradoException;
import br.com.tech.challenge.sistemapedido.usecase.gateway.ProdutoGateway;
import jakarta.inject.Named;

import java.util.List;

@Named
public class BuscarProdutoUseCase {
    private final ProdutoGateway repository;

    public BuscarProdutoUseCase(ProdutoGateway repository) {
        this.repository = repository;
    }

    public Produto buscarPorId(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }

    public List<Produto> buscarTodos() {
        return repository.buscarTodos();
    }
}
