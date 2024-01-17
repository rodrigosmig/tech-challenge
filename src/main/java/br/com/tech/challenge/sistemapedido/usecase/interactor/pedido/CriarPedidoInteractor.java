package br.com.tech.challenge.sistemapedido.usecase.interactor.pedido;

import br.com.tech.challenge.sistemapedido.domain.ItemPedido;
import br.com.tech.challenge.sistemapedido.domain.Pedido;
import br.com.tech.challenge.sistemapedido.domain.StatusPedido;
import br.com.tech.challenge.sistemapedido.domain.vo.Data;
import br.com.tech.challenge.sistemapedido.domain.vo.Preco;
import br.com.tech.challenge.sistemapedido.usecase.contract.pedido.CriarPedidoUseCase;
import br.com.tech.challenge.sistemapedido.usecase.contract.produto.BuscarProdutoUseCase;
import br.com.tech.challenge.sistemapedido.usecase.gateway.PedidoGateway;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Named
public class CriarPedidoInteractor implements CriarPedidoUseCase {
    private final BuscarProdutoUseCase buscarProdutoUseCase;
    private final PedidoGateway pedidoGateway;

    public CriarPedidoInteractor(BuscarProdutoUseCase buscarProdutoUseCase, PedidoGateway pedidoGateway) {
        this.buscarProdutoUseCase = buscarProdutoUseCase;
        this.pedidoGateway = pedidoGateway;
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
                .pago(Boolean.FALSE)
                .itens(novosItens)
                .build();

        pedido = pedidoGateway.salvar(pedido, novosItens);
        var itens = this.validarItens(itensPedido, pedido);
        pedido.adicionarItens(itens);
        pedido.calcularTotal();

        return pedidoGateway.salvar(pedido, itens);
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
