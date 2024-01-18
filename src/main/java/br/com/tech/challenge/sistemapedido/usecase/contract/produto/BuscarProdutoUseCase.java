package br.com.tech.challenge.sistemapedido.usecase.contract.produto;

import br.com.tech.challenge.sistemapedido.domain.Produto;

import java.util.List;

public interface BuscarProdutoUseCase {
    Produto buscarPorId(Long id);
    List<Produto> buscarTodos();
}
