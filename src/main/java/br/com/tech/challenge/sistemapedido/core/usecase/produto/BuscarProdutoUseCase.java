package br.com.tech.challenge.sistemapedido.core.usecase.produto;

import br.com.tech.challenge.sistemapedido.core.domain.Produto;

import java.util.List;

public interface BuscarProdutoUseCase {
    Produto buscarPorId(Long id);
    List<Produto> buscarTodos();
}
