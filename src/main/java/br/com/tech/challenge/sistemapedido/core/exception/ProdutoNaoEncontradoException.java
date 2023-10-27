package br.com.tech.challenge.sistemapedido.core.exception;

public class ProdutoNaoEncontradoException extends EntityNotFoundException {
    private static final String MENSAGEM = "Produto não encontrado";
    public ProdutoNaoEncontradoException() {
        super(MENSAGEM);
    }
}
