package br.com.tech.challenge.sistemapedido.core.usecase.pedido.impl;

import br.com.tech.challenge.sistemapedido.core.domain.Pedido;
import br.com.tech.challenge.sistemapedido.core.usecase.pedido.AlterarStatusPedidoUseCase;
import jakarta.inject.Named;

@Named
public class AlterarStatusPedidoUseCaseImpl implements AlterarStatusPedidoUseCase {

    @Override
    public void alterarParaEmPreparacao(Pedido pedido) {

    }

    @Override
    public void alterarParaPronto(Pedido pedido) {

    }

    @Override
    public void alterarParaFinalizado(Pedido pedido) {

    }
}
