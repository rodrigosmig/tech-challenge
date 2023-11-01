package br.com.tech.challenge.sistemapedido.core.repository;

import br.com.tech.challenge.sistemapedido.core.domain.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    Optional<Usuario> buscarPorId(Long id);

    List<Usuario> buscarTodos();

    Usuario salvar(Usuario usuario);

    void excluir(Usuario usuario);

    Optional<Usuario> buscarPorEmail(String email);

    Optional<Usuario> buscarPorCpfOuEmail(String cpf, String email);

    Optional<Usuario> buscarPorCpf(String cpf);

    Boolean existeCpf(String cpf);

    Boolean existeEmail(String email);
}