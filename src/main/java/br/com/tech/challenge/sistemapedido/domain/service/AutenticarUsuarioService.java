package br.com.tech.challenge.sistemapedido.domain.service;

import br.com.tech.challenge.sistemapedido.domain.Usuario;
import jakarta.inject.Named;

public interface AutenticarUsuarioService {
    String autenticar(String cpf, String senha);

    Usuario registrar(Usuario usuario);
}
