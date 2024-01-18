package br.com.tech.challenge.sistemapedido.infrastructure.repository;

import br.com.tech.challenge.sistemapedido.domain.Usuario;
import br.com.tech.challenge.sistemapedido.usecase.gateway.UsuarioGateway;
import br.com.tech.challenge.sistemapedido.infrastructure.mapper.UsuarioModelMapper;
import br.com.tech.challenge.sistemapedido.infrastructure.repository.jpa.UsuarioRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UsuarioGatewayImpl implements UsuarioGateway {
    private final UsuarioRepositoryJpa repository;
    private final UsuarioModelMapper usuarioMapper;

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return repository.findById(id)
                .map(usuarioMapper::toDomain);
    }

    @Override
    public List<Usuario> buscarTodos() {
        return repository.findAll().stream()
                .map(usuarioMapper::toDomain)
                .toList();
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        var produtoModel = repository.save(usuarioMapper.toModel(usuario));

        return usuarioMapper.toDomain(produtoModel);
    }

    @Override
    public void excluir(Usuario usuario) {
        var usuarioModel = usuarioMapper.toModel(usuario);
        repository.delete(usuarioModel);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return repository.findByEmail(email)
                .map(usuarioMapper::toDomain);
    }

    @Override
    public Optional<Usuario> buscarPorCpfOuEmail(String cpf, String email) {
        return repository.findByCpfOrEmail(cpf, email)
                .map(usuarioMapper::toDomain);
    }

    @Override
    public Optional<Usuario> buscarPorCpf(String cpf) {
        return repository.findByCpf(cpf)
                .map(usuarioMapper::toDomain);
    }

    @Override
    public Boolean existeCpf(String cpf) {
        return repository.existsByCpf(cpf);
    }

    @Override
    public Boolean existeEmail(String email) {
        return repository.existsByEmail(email);
    }
}
