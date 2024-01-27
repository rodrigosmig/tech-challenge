package br.com.tech.challenge.sistemapedido.usecase.interactor.usuario;

import br.com.tech.challenge.sistemapedido.domain.Usuario;
import br.com.tech.challenge.sistemapedido.domain.exception.UsuarioNaoEncontradoException;
import br.com.tech.challenge.sistemapedido.usecase.contract.usuario.ObterUsuarioUseCase;
import br.com.tech.challenge.sistemapedido.usecase.gateway.UsuarioGateway;
import jakarta.inject.Named;

@Named
public class ObterUsuarioUseCaseImpl implements ObterUsuarioUseCase {
    private final UsuarioGateway usuarioGateway;

    public ObterUsuarioUseCaseImpl(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public Usuario obterPorCPF(String cpf) {
        return usuarioGateway.buscarPorCpf(cpf)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(cpf));
    }
}
