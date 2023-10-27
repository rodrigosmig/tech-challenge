package br.com.tech.challenge.sistemapedido.core.domain;

import br.com.tech.challenge.sistemapedido.core.domain.vo.Descricao;
import br.com.tech.challenge.sistemapedido.core.domain.vo.Nome;
import br.com.tech.challenge.sistemapedido.core.domain.vo.Preco;

public class Produto {
    private Long id;
    private Nome nome;
    private Categoria categoria;
    private Descricao descricao;
    private Preco preco;

    public Produto(Long id, Nome nome, Categoria categoria, Descricao descricao, Preco preco) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Produto(Nome nome, Categoria categoria, Descricao descricao, Preco preco) {
        this.nome = nome;
        this.categoria = categoria;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public Nome getNome() {
        return nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Preco getPreco() {
        return preco;
    }

    public Descricao getDescricao() {
        return descricao;
    }
}
