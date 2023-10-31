package br.com.tech.challenge.sistemapedido.core.usecase.pedido;

import br.com.tech.challenge.sistemapedido.core.domain.Pedido;

public interface AlterarStatusPedidoUseCase {
    void alterarParaEmPreparacao(Pedido pedido);
    void alterarParaPronto(Pedido pedido);
    void alterarParaFinalizado(Pedido pedido);
}
