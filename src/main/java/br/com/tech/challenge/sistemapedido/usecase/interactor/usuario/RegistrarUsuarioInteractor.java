package br.com.tech.challenge.sistemapedido.usecase.interactor.usuario;

import br.com.tech.challenge.sistemapedido.domain.Usuario;
import br.com.tech.challenge.sistemapedido.domain.service.AutenticarUsuarioService;
import br.com.tech.challenge.sistemapedido.usecase.contract.usuario.RegistrarUsuarioUseCase;
import jakarta.inject.Named;

@Named
public class RegistrarUsuarioInteractor implements RegistrarUsuarioUseCase {
    private final AutenticarUsuarioService service;

    public RegistrarUsuarioInteractor(AutenticarUsuarioService service) {
        this.service = service;
    }


    @Override
    public Usuario registrar(Usuario usuario) {
        return service.registrar(usuario);
    }
}
