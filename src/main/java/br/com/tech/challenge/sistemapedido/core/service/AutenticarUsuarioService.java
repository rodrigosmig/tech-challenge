package br.com.tech.challenge.sistemapedido.core.service;

import br.com.tech.challenge.sistemapedido.core.domain.Usuario;
import jakarta.inject.Named;

@Named
public interface AutenticarUsuarioService {
    void autenticar(String cpf, String senha);
}
