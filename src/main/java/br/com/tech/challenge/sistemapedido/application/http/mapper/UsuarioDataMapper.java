package br.com.tech.challenge.sistemapedido.application.http.mapper;

import br.com.tech.challenge.sistemapedido.application.http.controller.v1.request.RegistrarUsuarioRequest;
import br.com.tech.challenge.sistemapedido.core.domain.Usuario;
import jakarta.inject.Named;

@Named
public class UsuarioDataMapper {

    public Usuario toDomain(RegistrarUsuarioRequest request) {
        return new Usuario(request.nome(),
                request.cpf(),
                request.email(),
                request.senha(),
                request.papeis()
        );
    }

}
