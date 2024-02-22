package br.com.tech.challenge.sistemapedido.application.controller;

import br.com.tech.challenge.sistemapedido.application.mapper.ProdutoDataMapper;
import br.com.tech.challenge.sistemapedido.application.request.ProdutoRequest;
import br.com.tech.challenge.sistemapedido.application.response.CadastrarProdutoResponse;
import br.com.tech.challenge.sistemapedido.application.response.ListarProdutosResponse;
import br.com.tech.challenge.sistemapedido.application.response.ProdutoResponse;
import br.com.tech.challenge.sistemapedido.usecase.gateway.ProdutoGateway;
import br.com.tech.challenge.sistemapedido.usecase.produto.AlterarProdutoUseCase;
import br.com.tech.challenge.sistemapedido.usecase.produto.BuscarProdutoUseCase;
import br.com.tech.challenge.sistemapedido.usecase.produto.CadastrarProdutoUseCase;
import br.com.tech.challenge.sistemapedido.usecase.produto.ExcluirProdutoUseCase;
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
        var buscarProdutoUseCase = new BuscarProdutoUseCase(this.produtoGateway);

        var produtos = buscarProdutoUseCase.buscarTodos();

        return new ListarProdutosResponse(produtoMapper.toList(produtos));
    }

    public ProdutoResponse buscar(Long id) {
        var buscarProdutoUseCase = new BuscarProdutoUseCase(this.produtoGateway);

        var produto = buscarProdutoUseCase.buscarPorId(id);

        return new ProdutoResponse(produtoMapper.toDTO(produto));
    }

    public CadastrarProdutoResponse criar(ProdutoRequest request) {
        var cadastrarProdutoUseCase = new CadastrarProdutoUseCase(this.produtoGateway);
        var produto = cadastrarProdutoUseCase.cadastrar(produtoMapper.toDomain(request));

        return new CadastrarProdutoResponse(produto.getId());
    }

    public ProdutoResponse alterar(Long id, ProdutoRequest request) {
        var alterarProdutoUseCase = new AlterarProdutoUseCase(this.produtoGateway);
        var produto = alterarProdutoUseCase.alterar(id, produtoMapper.toDomain(request));

        return new ProdutoResponse(produtoMapper.toDTO(produto));
    }

    public void excluir(Long id) {
        var excluirProdutoUseCase = new ExcluirProdutoUseCase(this.produtoGateway);
        excluirProdutoUseCase.excluir(id);
    }
}
