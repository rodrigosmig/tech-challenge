package br.com.tech.challenge.sistemapedido.usecase.contract.usuario;

import br.com.tech.challenge.sistemapedido.domain.Usuario;

public interface ObterUsuarioUseCase {
    Usuario obterPorCPF(String cpf);
}
