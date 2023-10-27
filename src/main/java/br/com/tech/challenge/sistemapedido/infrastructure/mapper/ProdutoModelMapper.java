package br.com.tech.challenge.sistemapedido.infrastructure.mapper;

import br.com.tech.challenge.sistemapedido.core.domain.Produto;
import br.com.tech.challenge.sistemapedido.core.domain.vo.Descricao;
import br.com.tech.challenge.sistemapedido.core.domain.vo.Nome;
import br.com.tech.challenge.sistemapedido.core.domain.vo.Preco;
import br.com.tech.challenge.sistemapedido.infrastructure.model.ProdutoModel;
import org.springframework.stereotype.Component;

@Component
public class ProdutoModelMapper {
    public Produto toDomain(ProdutoModel produto) {
        return new Produto(produto.getId(),
                new Nome(produto.getNome()),
                produto.getCategoria(),
                new Descricao(produto.getDescricao()),
                new Preco(produto.getPreco()));
    }

    public ProdutoModel toModel(Produto produto) {
        return ProdutoModel.builder()
                .id(produto.getId())
                .nome(produto.getNome().toString())
                .categoria(produto.getCategoria())
                .descricao(produto.getDescricao().toString())
                .preco(produto.getPreco().getPreco())
                .build();
    }
}
