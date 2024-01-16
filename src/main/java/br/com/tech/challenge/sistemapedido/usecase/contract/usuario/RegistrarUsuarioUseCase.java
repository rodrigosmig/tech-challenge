package br.com.tech.challenge.sistemapedido.usecase.contract.usuario;

import br.com.tech.challenge.sistemapedido.core.domain.Usuario;

public interface RegistrarUsuarioUseCase {
    Usuario registrar(Usuario usuario);
}
