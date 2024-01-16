package br.com.tech.challenge.sistemapedido.application.http.resource.v1.request;

public record AutenticarUsuarioRequest(String cpf,
                                       String senha) {
}
