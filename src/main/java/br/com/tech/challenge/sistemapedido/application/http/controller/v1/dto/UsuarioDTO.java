package br.com.tech.challenge.sistemapedido.application.http.controller.v1.dto;

import br.com.tech.challenge.sistemapedido.core.domain.Papel;

import java.util.Set;

public record UsuarioDTO(Long id,
                         String nome,
                         String cpf,
                         String email,
                         String senha,
                         Set<Papel> papeis) {
}
