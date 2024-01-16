package br.com.tech.challenge.sistemapedido.usecase.contract.produto;

import br.com.tech.challenge.sistemapedido.domain.Produto;

public interface CadastrarProdutoUseCase {
    Produto cadastrar(Produto produto);
}
