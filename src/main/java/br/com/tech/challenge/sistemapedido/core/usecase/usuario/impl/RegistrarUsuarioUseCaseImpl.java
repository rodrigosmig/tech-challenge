package br.com.tech.challenge.sistemapedido.core.usecase.usuario.impl;

import br.com.tech.challenge.sistemapedido.core.domain.Usuario;
import br.com.tech.challenge.sistemapedido.core.repository.UsuarioRepository;
import br.com.tech.challenge.sistemapedido.core.usecase.usuario.RegistrarUsuarioUseCase;
import jakarta.inject.Named;

@Named
public class RegistrarUsuarioUseCaseImpl implements RegistrarUsuarioUseCase {
    private final UsuarioRepository repository;

    public RegistrarUsuarioUseCaseImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Usuario registrar(Usuario usuario) {
        return repository.salvar(usuario);
    }
}
