package br.com.tech.challenge.sistemapedido.core.exception;

public abstract class EntityNotFoundException extends RuntimeException {
    protected EntityNotFoundException(String message) {
        super(message);
    }
}
