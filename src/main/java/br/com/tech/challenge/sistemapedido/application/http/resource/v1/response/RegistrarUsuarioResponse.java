package br.com.tech.challenge.sistemapedido.application.http.resource.v1.response;

import lombok.Getter;

@Getter
public class RegistrarUsuarioResponse {
    private Long idUsuario;

    public RegistrarUsuarioResponse(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
