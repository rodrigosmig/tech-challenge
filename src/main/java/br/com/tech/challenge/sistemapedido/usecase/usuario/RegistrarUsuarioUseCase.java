package br.com.tech.challenge.sistemapedido.usecase.usuario;

import br.com.tech.challenge.sistemapedido.domain.Usuario;
import br.com.tech.challenge.sistemapedido.domain.service.AutenticarUsuarioService;
import jakarta.inject.Named;

@Named
public class RegistrarUsuarioUseCase {
    private final AutenticarUsuarioService service;

    public RegistrarUsuarioUseCase(AutenticarUsuarioService service) {
        this.service = service;
    }

    public Usuario registrar(Usuario usuario) {
        return service.registrar(usuario);
    }
}
