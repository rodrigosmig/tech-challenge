package br.com.tech.challenge.sistemapedido.infrastructure.repository;

import br.com.tech.challenge.sistemapedido.domain.Produto;
import br.com.tech.challenge.sistemapedido.usecase.gateway.ProdutoGateway;
import br.com.tech.challenge.sistemapedido.infrastructure.mapper.ProdutoModelMapper;
import br.com.tech.challenge.sistemapedido.infrastructure.repository.jpa.ProdutoRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProdutoGatewayImpl implements ProdutoGateway {
    private final ProdutoRepositoryJpa repository;
    private final ProdutoModelMapper produtoMapper;

    @Override
    public Optional<Produto> buscarPorId(Long id) {
        return repository.findById(id)
                .map(produtoMapper::toDomain);
    }

    @Override
    public List<Produto> buscarTodos() {
        return repository.findAll().stream()
                .map(produtoMapper::toDomain)
                .toList();
    }

    @Override
    public Produto salvar(Produto produto) {
        var produtoModel = repository.save(produtoMapper.toModel(produto));

        return produtoMapper.toDomain(produtoModel);
    }

    @Override
    public void excluir(Produto produto) {
        var produtoModel = produtoMapper.toModel(produto);
        repository.delete(produtoModel);
    }
}
