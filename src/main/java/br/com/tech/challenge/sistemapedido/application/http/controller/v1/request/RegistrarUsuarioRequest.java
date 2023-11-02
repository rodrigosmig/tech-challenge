package br.com.tech.challenge.sistemapedido.application.http.controller.v1.request;

import br.com.tech.challenge.sistemapedido.core.domain.Papel;

import java.util.Set;

public record RegistrarUsuarioRequest(String nome,
                                      String cpf,
                                      String email,
                                      String senha,
                                      Set<Papel> papeis) {
}
