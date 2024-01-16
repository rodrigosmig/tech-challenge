package br.com.tech.challenge.sistemapedido.usecase.interactor.produto;

import br.com.tech.challenge.sistemapedido.core.domain.Produto;
import br.com.tech.challenge.sistemapedido.core.repository.ProdutoRepository;
import br.com.tech.challenge.sistemapedido.usecase.contract.produto.CadastrarProdutoUseCase;
import jakarta.inject.Named;

@Named
public class CadastrarProdutoInteractor implements CadastrarProdutoUseCase {
    private final ProdutoRepository repository;

    public CadastrarProdutoInteractor(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Produto cadastrar(Produto produto) {
        return repository.salvar(produto);
    }
}
