package br.com.tech.challenge.sistemapedido.core.usecase.pedido.impl;

import br.com.tech.challenge.sistemapedido.core.domain.ItemPedido;
import br.com.tech.challenge.sistemapedido.core.domain.Pedido;
import br.com.tech.challenge.sistemapedido.core.domain.StatusPedido;
import br.com.tech.challenge.sistemapedido.core.domain.vo.Data;
import br.com.tech.challenge.sistemapedido.core.domain.vo.Preco;
import br.com.tech.challenge.sistemapedido.core.repository.PedidoRepository;
import br.com.tech.challenge.sistemapedido.core.usecase.pedido.CriarPedidoUseCase;
import br.com.tech.challenge.sistemapedido.core.usecase.produto.BuscarProdutoUseCase;
import br.com.tech.challenge.sistemapedido.infrastructure.mapper.ItemPedidoModelMapper;
import br.com.tech.challenge.sistemapedido.infrastructure.repository.jpa.ItemPedidoRepositoryJpa;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Named
public class CriarPedidoUseCaseImpl implements CriarPedidoUseCase {
    private final BuscarProdutoUseCase buscarProdutoUseCase;
    @Inject
    private ItemPedidoRepositoryJpa itemRepository;
    @Inject
    private ItemPedidoModelMapper itemPedidoMapper;
    private final PedidoRepository pedidoRepository;

    public CriarPedidoUseCaseImpl(BuscarProdutoUseCase buscarProdutoUseCase, PedidoRepository pedidoRepository) {
        this.buscarProdutoUseCase = buscarProdutoUseCase;
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    @Transactional
    public Pedido criar(List<ItemPedido> itensPedido) {
        var novosItens = new LinkedList<ItemPedido>();
        var pedido = Pedido.builder()
                .status(StatusPedido.RECEBIDO)
                .dataCriacao(new Data(LocalDateTime.now()))
                .dataAtualizacao(new Data(LocalDateTime.now()))
                .total(new Preco(BigDecimal.ONE))
                .itens(novosItens)
                .build();

        pedido = pedidoRepository.salvar(pedido, novosItens);
        var itens = this.validarItens(itensPedido, pedido);
        pedido.adicionarItens(itens);
        pedido.calcularTotal();

        return pedidoRepository.salvar(pedido, itens);
    }

    private List<ItemPedido> validarItens(List<ItemPedido> itens, Pedido pedido) {
        return itens.stream()
                .map(itemPedido -> {
                    var produto = buscarProdutoUseCase.buscarPorId(itemPedido.getProduto().getId());
                    return ItemPedido.builder()
                            .quantidade(itemPedido.getQuantidade())
                            .observacao(itemPedido.getObservacao())
                            .produto(produto)
                            .pedido(pedido)
                            .build();
                })
                .toList();
    }
}
