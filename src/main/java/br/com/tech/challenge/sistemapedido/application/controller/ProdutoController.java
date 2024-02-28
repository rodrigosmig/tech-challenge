package br.com.tech.challenge.sistemapedido.application.controller;

import br.com.tech.challenge.sistemapedido.application.mapper.ProdutoDataMapper;
import br.com.tech.challenge.sistemapedido.application.request.ProdutoRequest;
import br.com.tech.challenge.sistemapedido.application.response.CadastrarProdutoResponse;
import br.com.tech.challenge.sistemapedido.application.response.ListarProdutosResponse;
import br.com.tech.challenge.sistemapedido.application.response.ProdutoResponse;
import br.com.tech.challenge.sistemapedido.usecase.gateway.ProdutoGateway;
import br.com.tech.challenge.sistemapedido.usecase.produto.*;
import jakarta.inject.Named;

@Named
public class ProdutoController {
    private final ProdutoGateway produtoGateway;
    private final ProdutoDataMapper produtoMapper;

    public ProdutoController(ProdutoGateway produtoGateway, ProdutoDataMapper produtoMapper) {
        this.produtoGateway = produtoGateway;
        this.produtoMapper = produtoMapper;
    }

    public ListarProdutosResponse listar() {
        var buscarTodosProdutosUseCase = new ListarProdutosUseCase(this.produtoGateway);

        var produtos = buscarTodosProdutosUseCase.executar();

        return new ListarProdutosResponse(produtoMapper.toList(produtos));
    }

    public ProdutoResponse buscar(Long id) {
        var buscarProdutoUseCase = new BuscarProdutoUseCase(this.produtoGateway);

        var produto = buscarProdutoUseCase.executar(id);

        return new ProdutoResponse(produtoMapper.toDTO(produto));
    }

    public CadastrarProdutoResponse criar(ProdutoRequest request) {
        var cadastrarProdutoUseCase = new CadastrarProdutoUseCase(this.produtoGateway);
        var produto = cadastrarProdutoUseCase.executar(produtoMapper.toDomain(request));

        return new CadastrarProdutoResponse(produto.getId());
    }

    public ProdutoResponse alterar(Long id, ProdutoRequest request) {
        var alterarProdutoUseCase = new AlterarProdutoUseCase(this.produtoGateway);
        var produto = alterarProdutoUseCase.executar(id, produtoMapper.toDomain(request));

        return new ProdutoResponse(produtoMapper.toDTO(produto));
    }

    public void excluir(Long id) {
        var excluirProdutoUseCase = new ExcluirProdutoUseCase(this.produtoGateway);
        excluirProdutoUseCase.executar(id);
    }
}
