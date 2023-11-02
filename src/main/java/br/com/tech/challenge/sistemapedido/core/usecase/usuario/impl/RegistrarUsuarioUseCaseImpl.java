package br.com.tech.challenge.sistemapedido.core.usecase.usuario.impl;

import br.com.tech.challenge.sistemapedido.core.domain.Usuario;
import br.com.tech.challenge.sistemapedido.core.service.AutenticarUsuarioService;
import br.com.tech.challenge.sistemapedido.core.usecase.usuario.RegistrarUsuarioUseCase;
import jakarta.inject.Named;

@Named
public class RegistrarUsuarioUseCaseImpl implements RegistrarUsuarioUseCase {
    private final AutenticarUsuarioService service;

    public RegistrarUsuarioUseCaseImpl(AutenticarUsuarioService service) {
        this.service = service;
    }


    @Override
    public Usuario registrar(Usuario usuario) {
        return service.registrar(usuario);
    }
}
