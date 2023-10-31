package br.com.tech.challenge.sistemapedido.core.usecase.usuario.impl;

import br.com.tech.challenge.sistemapedido.core.service.AutenticarUsuarioService;
import br.com.tech.challenge.sistemapedido.core.usecase.usuario.AutenticarUsuarioUseCase;
import jakarta.inject.Named;

@Named
public class AutenticarUsuarioUseCaseImpl implements AutenticarUsuarioUseCase {
    private final AutenticarUsuarioService service;

    public AutenticarUsuarioUseCaseImpl(AutenticarUsuarioService service) {
        this.service = service;
    }


    @Override
    public void autenticar(String cpf, String senha) {
        service.autenticar(cpf, senha);
    }
}
