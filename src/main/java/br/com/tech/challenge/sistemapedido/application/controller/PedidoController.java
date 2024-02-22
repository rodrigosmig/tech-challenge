package br.com.tech.challenge.sistemapedido.application.controller;

import br.com.tech.challenge.sistemapedido.application.mapper.ItemPedidoDataMapper;
import br.com.tech.challenge.sistemapedido.application.mapper.PedidoDataMapper;
import br.com.tech.challenge.sistemapedido.application.request.PedidoRequest;
import br.com.tech.challenge.sistemapedido.application.response.CadastrarPedidoResponse;
import br.com.tech.challenge.sistemapedido.application.response.ListarPedidosResponse;
import br.com.tech.challenge.sistemapedido.application.response.StatusPedidoResponse;
import br.com.tech.challenge.sistemapedido.usecase.gateway.PedidoGateway;
import br.com.tech.challenge.sistemapedido.usecase.gateway.ProdutoGateway;
import br.com.tech.challenge.sistemapedido.usecase.gateway.UsuarioGateway;
import br.com.tech.challenge.sistemapedido.usecase.pedido.AlterarStatusPedidoUseCase;
import br.com.tech.challenge.sistemapedido.usecase.pedido.BuscarPedidoUseCase;
import br.com.tech.challenge.sistemapedido.usecase.pedido.CriarPedidoUseCase;
import br.com.tech.challenge.sistemapedido.usecase.produto.BuscarProdutoUseCase;
import br.com.tech.challenge.sistemapedido.usecase.usuario.ObterUsuarioUseCase;
import jakarta.inject.Named;

@Named
public class PedidoController {
    private final PedidoGateway pedidoGateway;
    private final ProdutoGateway produtoGateway;
    private final UsuarioGateway usuarioGateway;
    private final PedidoDataMapper pedidoMapper;
    private final ItemPedidoDataMapper itemPedidoMapper;

    public PedidoController(PedidoGateway pedidoGateway,
                            ProdutoGateway produtoGateway,
                            UsuarioGateway usuarioGateway,
                            PedidoDataMapper pedidoMapper,
                            ItemPedidoDataMapper itemPedidoMapper) {
        this.pedidoGateway = pedidoGateway;
        this.produtoGateway = produtoGateway;
        this.usuarioGateway = usuarioGateway;
        this.pedidoMapper = pedidoMapper;
        this.itemPedidoMapper = itemPedidoMapper;
    }

    public CadastrarPedidoResponse criar(PedidoRequest request) {
        var buscarProdutoUseCase = new BuscarProdutoUseCase(this.produtoGateway);
        var obterUsuarioUseCase = new ObterUsuarioUseCase(this.usuarioGateway);
        var criarPedidoUseCase = new CriarPedidoUseCase(buscarProdutoUseCase, obterUsuarioUseCase, this.pedidoGateway);

        var pedido = criarPedidoUseCase.criar(itemPedidoMapper.toDomainList(request.itens()), request.cpf());

        return new CadastrarPedidoResponse(pedido.getId());
    }

    public ListarPedidosResponse listar() {
        var buscarPedidoUseCase = new BuscarPedidoUseCase(this.pedidoGateway);
        var pedidos = buscarPedidoUseCase.buscarTodos();

        return new ListarPedidosResponse(pedidoMapper.toList(pedidos));
    }

    public StatusPedidoResponse verificarStatus(Long id) {
        var buscarPedidoUseCase = new BuscarPedidoUseCase(this.pedidoGateway);
        var pedido = buscarPedidoUseCase.buscarPorId(id);

        return new StatusPedidoResponse(pedido.estaPago());
    }

    //TODO converter ConfirmarPagamento em Caso de Uso
//    public void receberConfirmacaoPagamento(Long id) {
//        confirmarPagamento.confirmarPagamento(id);
//    }

    //TODO converter ConfirmarPagamento em Caso de Uso
//    public void pagar(Long idPedido) {
//        var pagarPedidoUseCase = new GerarPagamentoService(this.pedidoGateway);
//        pagarPedidoUseCase.pagar(idPedido);
//    }
//
//    public File gerarPagamento(Long idPedido) {
//        return pagarPedidoUseCase.gerarPagamento(idPedido);
//    }

    public void preparacao(Long idPedido) {
        var buscarPedidoUseCase = new BuscarPedidoUseCase(this.pedidoGateway);
        var alterarStatusPedidoUseCase = new AlterarStatusPedidoUseCase(this.pedidoGateway, buscarPedidoUseCase);
        alterarStatusPedidoUseCase.alterarParaEmPreparacao(idPedido);
    }

    public void pronto(Long idPedido) {
        var buscarPedidoUseCase = new BuscarPedidoUseCase(this.pedidoGateway);
        var alterarStatusPedidoUseCase = new AlterarStatusPedidoUseCase(this.pedidoGateway, buscarPedidoUseCase);
        alterarStatusPedidoUseCase.alterarParaPronto(idPedido);
    }

    public void finalizado(Long idPedido) {
        var buscarPedidoUseCase = new BuscarPedidoUseCase(this.pedidoGateway);
        var alterarStatusPedidoUseCase = new AlterarStatusPedidoUseCase(this.pedidoGateway, buscarPedidoUseCase);
        alterarStatusPedidoUseCase.alterarParaFinalizado(idPedido);
    }
}
