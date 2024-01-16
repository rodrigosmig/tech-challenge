package br.com.tech.challenge.sistemapedido.usecase.repository;

import br.com.tech.challenge.sistemapedido.core.domain.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoGateway {
    Optional<Produto> buscarPorId(Long id);
    List<Produto> buscarTodos();
    Produto salvar(Produto produto);
    void excluir(Produto produto);
}
