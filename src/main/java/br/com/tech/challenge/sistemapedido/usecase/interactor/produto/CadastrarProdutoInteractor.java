package br.com.tech.challenge.sistemapedido.usecase.interactor.produto;

import br.com.tech.challenge.sistemapedido.domain.Produto;
import br.com.tech.challenge.sistemapedido.usecase.gateway.ProdutoGateway;
import br.com.tech.challenge.sistemapedido.usecase.contract.produto.CadastrarProdutoUseCase;
import jakarta.inject.Named;

@Named
public class CadastrarProdutoInteractor implements CadastrarProdutoUseCase {
    private final ProdutoGateway repository;

    public CadastrarProdutoInteractor(ProdutoGateway repository) {
        this.repository = repository;
    }

    @Override
    public Produto cadastrar(Produto produto) {
        return repository.salvar(produto);
    }
}
