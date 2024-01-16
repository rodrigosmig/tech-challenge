package br.com.tech.challenge.sistemapedido.usecase.interactor.usuario;

import br.com.tech.challenge.sistemapedido.core.service.AutenticarUsuarioService;
import br.com.tech.challenge.sistemapedido.usecase.contract.usuario.AutenticarUsuarioUseCase;
import jakarta.inject.Named;

@Named
public class AutenticarUsuarioInteractor implements AutenticarUsuarioUseCase {
    private final AutenticarUsuarioService service;

    public AutenticarUsuarioInteractor(AutenticarUsuarioService service) {
        this.service = service;
    }


    @Override
    public String autenticar(String cpf, String senha) {
        return service.autenticar(cpf, senha);
    }
}
