package br.com.tech.challenge.sistemapedido.usecase.contract.pedido;

public interface AlterarStatusPedidoUseCase {
    void alterarParaEmPreparacao(Long idPedido);
    void alterarParaPronto(Long idPedido);
    void alterarParaFinalizado(Long idPedido);
}
