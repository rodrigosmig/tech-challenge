package br.com.tech.challenge.sistemapedido.core.usecase.produto.impl;

import br.com.tech.challenge.sistemapedido.core.domain.Produto;
import br.com.tech.challenge.sistemapedido.core.repository.ProdutoRepository;
import br.com.tech.challenge.sistemapedido.core.usecase.produto.AlterarProdutoUseCase;
import br.com.tech.challenge.sistemapedido.core.usecase.produto.BuscarProdutoUseCase;
import jakarta.inject.Named;

@Named
public class AlterarProdutoUseCaseImpl implements AlterarProdutoUseCase {
    private final ProdutoRepository repository;
    private final BuscarProdutoUseCase buscarProdutoUseCase;

    public AlterarProdutoUseCaseImpl(ProdutoRepository repository, BuscarProdutoUseCase buscarProdutoUseCase) {
        this.repository = repository;
        this.buscarProdutoUseCase = buscarProdutoUseCase;
    }

    @Override
    public Produto alterar(Long id, Produto produtoAlterado) {
        var produtoAtual = buscarProdutoUseCase.buscarPorId(id);

        var produtoAtualizado = atualizarDados(produtoAlterado, produtoAtual);

        return repository.salvar(produtoAtualizado);
    }

    private Produto atualizarDados(Produto novoProduto, Produto produtoAtual) {
        return Produto.builder()
                .id(produtoAtual.getId())
                .nome(novoProduto.getNome())
                .categoria(novoProduto.getCategoria())
                .descricao(novoProduto.getDescricao())
                .preco(novoProduto.getPreco())
                .build();
    }
}
