package br.com.tech.challenge.sistemapedido.core.service;

import br.com.tech.challenge.sistemapedido.core.domain.Usuario;
import jakarta.inject.Named;

@Named
public interface AutenticarUsuarioService {
    String autenticar(String cpf, String senha);

    Usuario registrar(Usuario usuario);
}
