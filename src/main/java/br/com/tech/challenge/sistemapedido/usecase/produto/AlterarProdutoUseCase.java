package br.com.tech.challenge.sistemapedido.usecase.produto;

import br.com.tech.challenge.sistemapedido.domain.Produto;
import br.com.tech.challenge.sistemapedido.usecase.gateway.ProdutoGateway;
import br.com.tech.challenge.sistemapedido.usecase.contract.produto.BuscarProdutoUseCase;
import jakarta.inject.Named;

@Named
public class AlterarProdutoUseCase {
    private final ProdutoGateway repository;
    private final BuscarProdutoUseCase buscarProdutoUseCase;

    public AlterarProdutoUseCase(ProdutoGateway repository, BuscarProdutoUseCase buscarProdutoUseCase) {
        this.repository = repository;
        this.buscarProdutoUseCase = buscarProdutoUseCase;
    }

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
