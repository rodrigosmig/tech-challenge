package br.com.tech.challenge.sistemapedido.core.usecase.pedido;

import br.com.tech.challenge.sistemapedido.core.domain.Pedido;

public interface AlterarStatusPedidoUseCase {
    void alterarParaEmPreparacao(Long idPedido);
    void alterarParaPronto(Long idPedido);
    void alterarParaFinalizado(Long idPedido);
}
