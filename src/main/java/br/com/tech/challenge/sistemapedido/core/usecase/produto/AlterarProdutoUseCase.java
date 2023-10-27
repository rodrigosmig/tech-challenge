package br.com.tech.challenge.sistemapedido.core.usecase.produto;

import br.com.tech.challenge.sistemapedido.core.domain.Produto;

public interface AlterarProdutoUseCase {
    Produto alterar(Long id, Produto alterar);
}
