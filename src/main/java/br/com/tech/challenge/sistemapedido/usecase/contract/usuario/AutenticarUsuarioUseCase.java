package br.com.tech.challenge.sistemapedido.usecase.contract.usuario;

public interface AutenticarUsuarioUseCase {
    String autenticar(String cpf, String senha);
}
