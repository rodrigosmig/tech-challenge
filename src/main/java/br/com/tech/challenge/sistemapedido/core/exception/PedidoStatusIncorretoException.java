package br.com.tech.challenge.sistemapedido.core.exception;

public class PedidoStatusIncorretoException extends RuntimeException {
    public PedidoStatusIncorretoException(String message) {
        super(message);
    }
}
