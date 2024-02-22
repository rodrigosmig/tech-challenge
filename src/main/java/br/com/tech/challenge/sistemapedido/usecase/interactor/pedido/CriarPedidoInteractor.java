package br.com.tech.challenge.sistemapedido.usecase.interactor.pedido;

import br.com.tech.challenge.sistemapedido.domain.ItemPedido;
import br.com.tech.challenge.sistemapedido.domain.Pedido;
import br.com.tech.challenge.sistemapedido.domain.StatusPedido;
import br.com.tech.challenge.sistemapedido.domain.vo.Data;
import br.com.tech.challenge.sistemapedido.domain.vo.Preco;
import br.com.tech.challenge.sistemapedido.usecase.contract.pedido.CriarPedidoUseCase;
import br.com.tech.challenge.sistemapedido.usecase.contract.produto.BuscarProdutoUseCase;
import br.com.tech.challenge.sistemapedido.usecase.contract.usuario.ObterUsuarioUseCase;
import br.com.tech.challenge.sistemapedido.usecase.gateway.PedidoGateway;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Named
public class CriarPedidoInteractor implements CriarPedidoUseCase {
    private final BuscarProdutoUseCase buscarProdutoUseCase;
    private final ObterUsuarioUseCase obterUsuarioUseCase;
    private final PedidoGateway pedidoGateway;

    public CriarPedidoInteractor(BuscarProdutoUseCase buscarProdutoUseCase, ObterUsuarioUseCase obterUsuarioUseCase, PedidoGateway pedidoGateway) {
        this.buscarProdutoUseCase = buscarProdutoUseCase;
        this.obterUsuarioUseCase = obterUsuarioUseCase;
        this.pedidoGateway = pedidoGateway;
    }

    @Override
    @Transactional
    public Pedido criar(List<ItemPedido> itensPedido, String cpf) {
        var novosItens = new LinkedList<ItemPedido>();
        var pedido = Pedido.builder()
                .status(StatusPedido.RECEBIDO)
                .dataCriacao(new Data(LocalDateTime.now()))
                .dataAtualizacao(new Data(LocalDateTime.now()))
                .total(new Preco(BigDecimal.ONE))
                .pago(Boolean.FALSE)
                .itens(novosItens)
                .build();

        pedido = pedidoGateway.salvar(pedido);
        var itens = this.validarItens(itensPedido, pedido);
        pedido.adicionarItens(itens);
        pedido.calcularTotal();

        if(validarCpf(cpf)) {
            var usuario = obterUsuarioUseCase.obterPorCPF(cpf);
            pedido.adicionarUsuario(usuario);
        }

        return pedidoGateway.salvar(pedido);
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

    private boolean validarCpf(String cpf) {
        return Objects.nonNull(cpf)
                && cpf.length() == 11;
    }
}
