package br.com.tech.challenge.sistemapedido.core.usecase.produto;

import br.com.tech.challenge.sistemapedido.core.domain.Produto;

public interface CadastrarProdutoUseCase {
    Produto cadastrar(Produto produto);
}
