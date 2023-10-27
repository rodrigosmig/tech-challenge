package br.com.tech.challenge.sistemapedido.core.usecase.produto.impl;

import br.com.tech.challenge.sistemapedido.core.domain.Produto;
import br.com.tech.challenge.sistemapedido.core.repository.ProdutoRepository;
import br.com.tech.challenge.sistemapedido.core.usecase.produto.BuscarProdutoUseCase;
import jakarta.inject.Named;

import java.util.List;

@Named
public class BuscarProdutoUseCaseImpl implements BuscarProdutoUseCase {
    private final ProdutoRepository repository;

    public BuscarProdutoUseCaseImpl(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Produto buscarPorId(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    @Override
    public List<Produto> buscarTodos() {
        return repository.buscarTodos();
    }


}
