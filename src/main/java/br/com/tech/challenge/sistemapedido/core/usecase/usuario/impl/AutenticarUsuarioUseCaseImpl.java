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
    public String autenticar(String cpf, String senha) {
        return service.autenticar(cpf, senha);
    }
}
