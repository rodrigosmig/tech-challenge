package br.com.tech.challenge.sistemapedido.application.http.mapper;

import br.com.tech.challenge.sistemapedido.application.http.controller.v1.dto.ProdutoDTO;
import br.com.tech.challenge.sistemapedido.application.http.controller.v1.request.ProdutoRequest;
import br.com.tech.challenge.sistemapedido.core.domain.Categoria;
import br.com.tech.challenge.sistemapedido.core.domain.Produto;
import br.com.tech.challenge.sistemapedido.core.domain.vo.Descricao;
import br.com.tech.challenge.sistemapedido.core.domain.vo.Nome;
import br.com.tech.challenge.sistemapedido.core.domain.vo.Preco;
import jakarta.inject.Named;

import java.util.List;

@Named
public class ProdutoDataMapper {
    public ProdutoDTO toDTO(Produto produto) {
        return new ProdutoDTO(produto.getId(),
                produto.getNome().toString(),
                produto.getCategoria(),
                produto.getDescricao().toString(),
                produto.getPreco().getPreco());
    }

    public Produto toDomain(ProdutoRequest request) {
        return new Produto(new Nome(request.nome()),
                Categoria.valueOf(request.categoria().toUpperCase()),
                new Descricao(request.descricao()),
                new Preco(request.preco()));
    }

    public List<ProdutoDTO> toList(List<Produto> produtos) {
        return produtos.stream()
                .map(this::toDTO)
                .toList();
    }
}
