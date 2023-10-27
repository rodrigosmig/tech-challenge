package br.com.tech.challenge.sistemapedido.infrastructure.mapper;

import br.com.tech.challenge.sistemapedido.core.domain.Produto;
import br.com.tech.challenge.sistemapedido.infrastructure.model.ProdutoModel;
import org.springframework.stereotype.Component;

@Component
public class ProdutoModelMapper {
    public Produto toDomain(ProdutoModel produto) {
        return new Produto(produto.getId(),
                produto.getNome(),
                produto.getCategoria(),
                produto.getDescricao(),
                produto.getPreco());
    }

    public ProdutoModel toModel(Produto produto) {
        return ProdutoModel.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .categoria(produto.getCategoria())
                .descricao(produto.getDescricao())
                .preco(produto.getPreco())
                .build();
    }
}
