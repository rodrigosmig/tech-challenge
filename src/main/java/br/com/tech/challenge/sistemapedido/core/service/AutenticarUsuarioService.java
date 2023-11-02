package br.com.tech.challenge.sistemapedido.core.service;

import jakarta.inject.Named;

@Named
public interface AutenticarUsuarioService {
    String autenticar(String cpf, String senha);
}
