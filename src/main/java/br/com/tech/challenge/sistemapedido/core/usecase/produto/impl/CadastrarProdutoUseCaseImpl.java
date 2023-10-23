package br.com.tech.challenge.sistemapedido.core.usecase.produto.impl;

import br.com.tech.challenge.sistemapedido.core.domain.Produto;
import br.com.tech.challenge.sistemapedido.core.repository.ProdutoRepository;
import br.com.tech.challenge.sistemapedido.core.usecase.produto.CadastrarProdutoUseCase;
import jakarta.inject.Named;

@Named
public class CadastrarProdutoUseCaseImpl implements CadastrarProdutoUseCase {
    private final ProdutoRepository repository;

    public CadastrarProdutoUseCaseImpl(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Produto cadastrar(Produto produto) {
        return repository.salvar(produto);
    }
}
