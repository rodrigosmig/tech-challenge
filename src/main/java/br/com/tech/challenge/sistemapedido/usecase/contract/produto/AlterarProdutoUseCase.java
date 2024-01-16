package br.com.tech.challenge.sistemapedido.usecase.contract.produto;

import br.com.tech.challenge.sistemapedido.domain.Produto;

public interface AlterarProdutoUseCase {
    Produto alterar(Long id, Produto alterar);
}
