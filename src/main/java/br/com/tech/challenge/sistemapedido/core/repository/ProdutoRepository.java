package br.com.tech.challenge.sistemapedido.core.repository;

import br.com.tech.challenge.sistemapedido.core.domain.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository {
    Optional<Produto> buscarPorId(Long id);
    List<Produto> buscarTodos();
    Produto salvar(Produto produto);
    void excluir(Produto produto);
}
