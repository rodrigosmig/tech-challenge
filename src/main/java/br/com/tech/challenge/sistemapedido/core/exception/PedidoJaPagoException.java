package br.com.tech.challenge.sistemapedido.core.exception;

public class PedidoJaPagoException extends RuntimeException {
    private static final String MENSAGEM = "Pedido já está pago id: %s";
    public PedidoJaPagoException(Long id) {
        super(String.format(MENSAGEM, id));
    }
}
