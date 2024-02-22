package br.com.tech.challenge.sistemapedido.usecase.produto;

import br.com.tech.challenge.sistemapedido.domain.Produto;
import br.com.tech.challenge.sistemapedido.usecase.gateway.ProdutoGateway;
import jakarta.inject.Named;

@Named
public class AlterarProdutoUseCase {
    private final ProdutoGateway produtoGateway;

    public AlterarProdutoUseCase(ProdutoGateway produtoGateway) {
        this.produtoGateway = produtoGateway;
    }

    public Produto alterar(Long id, Produto produtoAlterado) {
        var buscarProdutoUseCase = new BuscarProdutoUseCase(this.produtoGateway);

        var produtoAtual = buscarProdutoUseCase.buscarPorId(id);

        var produtoAtualizado = atualizarDados(produtoAlterado, produtoAtual);

        return produtoGateway.salvar(produtoAtualizado);
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
