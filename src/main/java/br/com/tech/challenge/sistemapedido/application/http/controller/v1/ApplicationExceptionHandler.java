package br.com.tech.challenge.sistemapedido.application.http.controller.v1;

import br.com.tech.challenge.sistemapedido.application.http.controller.v1.dto.InputErrorDTO;
import br.com.tech.challenge.sistemapedido.core.exception.EntityNotFoundException;
import br.com.tech.challenge.sistemapedido.core.exception.PedidoJaPagoException;
import br.com.tech.challenge.sistemapedido.core.exception.PedidoNaoPagoException;
import br.com.tech.challenge.sistemapedido.core.exception.PedidoStatusIncorretoException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<InputErrorDTO> constraintViolation(ConstraintViolationException exception) {
        return new ResponseEntity<>(new InputErrorDTO(exception.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<InputErrorDTO> constraintViolation(EntityNotFoundException exception) {
        return new ResponseEntity<>(new InputErrorDTO(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({PedidoJaPagoException.class, PedidoNaoPagoException.class, PedidoStatusIncorretoException.class})
    public ResponseEntity<InputErrorDTO> pedidoJaPago(RuntimeException exception) {
        return new ResponseEntity<>(new InputErrorDTO(exception.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<InputErrorDTO> constraintViolation(UsernameNotFoundException exception) {
        return new ResponseEntity<>(new InputErrorDTO(exception.getMessage()), HttpStatus.NOT_FOUND);
    }
}
