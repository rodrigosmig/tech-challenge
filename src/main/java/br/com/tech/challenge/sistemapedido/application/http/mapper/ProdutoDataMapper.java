package br.com.tech.challenge.sistemapedido.application.http.mapper;

import br.com.tech.challenge.sistemapedido.application.http.controller.v1.dto.ProdutoDTO;
import br.com.tech.challenge.sistemapedido.application.http.controller.v1.request.ProdutoRequest;
import br.com.tech.challenge.sistemapedido.core.domain.Categoria;
import br.com.tech.challenge.sistemapedido.core.domain.Produto;
import jakarta.inject.Named;

import java.util.List;

@Named
public class ProdutoDataMapper {
    public ProdutoDTO toDTO(Produto produto) {
        return new ProdutoDTO(produto.getId(),
                produto.getNome(),
                produto.getCategoria(),
                produto.getDescricao(),
                produto.getPreco());
    }

    public Produto toDomain(ProdutoRequest request) {
        return new Produto(request.nome(),
                Categoria.valueOf(request.categoria().toUpperCase()),
                request.descricao(),
                request.preco());
    }

    public List<ProdutoDTO> toList(List<Produto> produtos) {
        return produtos.stream()
                .map(this::toDTO)
                .toList();
    }
}
