package br.com.tech.challenge.sistemapedido.application.controller;

import br.com.tech.challenge.sistemapedido.application.mapper.ItemPedidoDataMapper;
import br.com.tech.challenge.sistemapedido.application.mapper.PedidoDataMapper;
import br.com.tech.challenge.sistemapedido.application.request.PedidoRequest;
import br.com.tech.challenge.sistemapedido.application.response.CadastrarPedidoResponse;
import br.com.tech.challenge.sistemapedido.application.response.ListarPedidosResponse;
import br.com.tech.challenge.sistemapedido.application.response.StatusPedidoResponse;
import br.com.tech.challenge.sistemapedido.usecase.contract.pedido.AlterarStatusPedidoUseCase;
import br.com.tech.challenge.sistemapedido.usecase.contract.pedido.BuscarPedidoUseCase;
import br.com.tech.challenge.sistemapedido.usecase.contract.pedido.CriarPedidoUseCase;
import br.com.tech.challenge.sistemapedido.usecase.contract.pedido.PagarPedidoUseCase;
import jakarta.inject.Named;

import java.io.File;

@Named
public class PedidoController {
    private final CriarPedidoUseCase criarPedidoUseCase;
    private final BuscarPedidoUseCase buscarPedidoUseCase;
    private final PagarPedidoUseCase pagarPedidoUseCase;
    private final AlterarStatusPedidoUseCase alterarStatusPedidoUseCase;
    private final ItemPedidoDataMapper itemPedidoMapper;
    private final PedidoDataMapper pedidoMapper;

    public PedidoController(CriarPedidoUseCase criarPedidoUseCase,
                            BuscarPedidoUseCase buscarPedidoUseCase,
                            PagarPedidoUseCase pagarPedidoUseCase,
                            AlterarStatusPedidoUseCase alterarStatusPedidoUseCase,
                            ItemPedidoDataMapper itemPedidoMapper,
                            PedidoDataMapper pedidoMapper) {
        this.criarPedidoUseCase = criarPedidoUseCase;
        this.buscarPedidoUseCase = buscarPedidoUseCase;
        this.pagarPedidoUseCase = pagarPedidoUseCase;
        this.alterarStatusPedidoUseCase = alterarStatusPedidoUseCase;
        this.itemPedidoMapper = itemPedidoMapper;
        this.pedidoMapper = pedidoMapper;
    }

    public CadastrarPedidoResponse criar(PedidoRequest request) {
        var pedido = criarPedidoUseCase.criar(itemPedidoMapper.toDomainList(request.itens()));

        return new CadastrarPedidoResponse(pedido.getId());
    }

    public ListarPedidosResponse listar() {
        var pedidos = buscarPedidoUseCase.buscarTodos();

        return new ListarPedidosResponse(pedidoMapper.toList(pedidos));
    }

    public StatusPedidoResponse verificarStatus(Long id) {
        var pedido = buscarPedidoUseCase.buscarPorId(id);

        return new StatusPedidoResponse(pedido.estaPago());
    }

    public File gerarPagamento(Long idPedido) {
        return pagarPedidoUseCase.gerarPagamento(idPedido);
    }

    public void pagar(Long idPedido) {
        pagarPedidoUseCase.pagar(idPedido);
    }

    public void preparacao(Long idPedido) {
        alterarStatusPedidoUseCase.alterarParaEmPreparacao(idPedido);
    }

    public void pronto(Long idPedido) {
        alterarStatusPedidoUseCase.alterarParaPronto(idPedido);
    }

    public void finalizado(Long idPedido) {
        alterarStatusPedidoUseCase.alterarParaFinalizado(idPedido);
    }
}
