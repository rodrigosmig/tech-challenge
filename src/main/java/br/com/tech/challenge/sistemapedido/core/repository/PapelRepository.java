package br.com.tech.challenge.sistemapedido.core.repository;

import br.com.tech.challenge.sistemapedido.core.domain.Papel;

import java.util.List;
import java.util.Optional;

public interface PapelRepository {
    Optional<Papel> buscarPorId(Long id);

    List<Papel> buscarTodos();

    Papel salvar(Papel papel);

    void excluir(Papel papel);

    Optional<Papel> buscarPorNome(String nome);
}
