package br.com.tech.challenge.sistemapedido.usecase.usuario;

import br.com.tech.challenge.sistemapedido.domain.service.AutenticarUsuarioService;
import jakarta.inject.Named;

@Named
public class AutenticarUsuarioUseCase {
    private final AutenticarUsuarioService service;

    public AutenticarUsuarioUseCase(AutenticarUsuarioService service) {
        this.service = service;
    }

    public String autenticar(String cpf, String senha) {
        return service.autenticar(cpf, senha);
    }
}
