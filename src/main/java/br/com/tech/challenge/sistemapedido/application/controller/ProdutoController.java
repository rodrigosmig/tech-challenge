package br.com.tech.challenge.sistemapedido.application.controller;

import br.com.tech.challenge.sistemapedido.application.mapper.ProdutoDataMapper;
import br.com.tech.challenge.sistemapedido.application.request.ProdutoRequest;
import br.com.tech.challenge.sistemapedido.application.response.CadastrarProdutoResponse;
import br.com.tech.challenge.sistemapedido.application.response.ListarProdutosResponse;
import br.com.tech.challenge.sistemapedido.application.response.ProdutoResponse;
import br.com.tech.challenge.sistemapedido.usecase.contract.produto.AlterarProdutoUseCase;
import br.com.tech.challenge.sistemapedido.usecase.contract.produto.BuscarProdutoUseCase;
import br.com.tech.challenge.sistemapedido.usecase.contract.produto.CadastrarProdutoUseCase;
import br.com.tech.challenge.sistemapedido.usecase.contract.produto.ExcluirProdutoUseCase;
import jakarta.inject.Named;

@Named
public class ProdutoController {
    private final BuscarProdutoUseCase buscarProdutoUseCase;
    private final CadastrarProdutoUseCase cadastrarProdutoUseCase;
    private final AlterarProdutoUseCase alterarProdutoUseCase;
    private final ExcluirProdutoUseCase excluirProdutoUseCase;
    private final ProdutoDataMapper produtoMapper;


    public ListarProdutosResponse listar() {
        var produtos = buscarProdutoUseCase.buscarTodos();

        return new ListarProdutosResponse(produtoMapper.toList(produtos));
    }

    public ProdutoResponse buscar(Long id) {
        var produto = buscarProdutoUseCase.buscarPorId(id);

        return new ProdutoResponse(produtoMapper.toDTO(produto));
    }

    public CadastrarProdutoResponse criar(ProdutoRequest request) {
        var produto = cadastrarProdutoUseCase.cadastrar(produtoMapper.toDomain(request));

        return new CadastrarProdutoResponse(produto.getId());
    }

    public ProdutoResponse alterar(Long id, ProdutoRequest request) {
        var produto = alterarProdutoUseCase.alterar(id, produtoMapper.toDomain(request));

        return new ProdutoResponse(produtoMapper.toDTO(produto));
    }

    public void excluir(Long id) {
        excluirProdutoUseCase.excluir(id);
    }
}
