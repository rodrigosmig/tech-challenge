package br.com.tech.challenge.sistemapedido.core.exception;

import org.springframework.http.HttpStatus;

public class SistemaPedidosAPIException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public SistemaPedidosAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public SistemaPedidosAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
