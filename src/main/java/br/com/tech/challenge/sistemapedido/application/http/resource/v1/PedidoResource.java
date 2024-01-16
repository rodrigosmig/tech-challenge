package br.com.tech.challenge.sistemapedido.application.http.resource.v1;

import br.com.tech.challenge.sistemapedido.application.request.PedidoRequest;
import br.com.tech.challenge.sistemapedido.application.response.CadastrarPedidoResponse;
import br.com.tech.challenge.sistemapedido.application.response.ListarPedidosResponse;
import br.com.tech.challenge.sistemapedido.application.mapper.ItemPedidoDataMapper;
import br.com.tech.challenge.sistemapedido.application.mapper.PedidoDataMapper;
import br.com.tech.challenge.sistemapedido.usecase.contract.pedido.AlterarStatusPedidoUseCase;
import br.com.tech.challenge.sistemapedido.usecase.contract.pedido.BuscarPedidoUseCase;
import br.com.tech.challenge.sistemapedido.usecase.contract.pedido.CriarPedidoUseCase;
import br.com.tech.challenge.sistemapedido.usecase.contract.pedido.PagarPedidoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/pedidos")
public class PedidoResource {
    private final CriarPedidoUseCase criarPedidoUseCase;
    private final BuscarPedidoUseCase buscarPedidoUseCase;
    private final PagarPedidoUseCase pagarPedidoUseCase;
    private final AlterarStatusPedidoUseCase alterarStatusPedidoUseCase;

    private final ItemPedidoDataMapper itemPedidoMapper;
    private final PedidoDataMapper pedidoMapper;

    @PostMapping
    public ResponseEntity<CadastrarPedidoResponse> criar(@RequestBody PedidoRequest request) {
        var pedido = criarPedidoUseCase.criar(itemPedidoMapper.toDomainList(request.itens()));
        var resposta = new CadastrarPedidoResponse(pedido.getId());

        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ListarPedidosResponse> listar() {
        var pedidos = buscarPedidoUseCase.buscarTodos();
        var resposta = new ListarPedidosResponse(pedidoMapper.toList(pedidos));

        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    @PostMapping("{idPedido}/pagar")
    public ResponseEntity<Void> pagar(@PathVariable Long idPedido) {
        pagarPedidoUseCase.pagar(idPedido);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("preparacao/{idPedido}")
    public ResponseEntity<Void> preparacao(@PathVariable Long idPedido) {
        alterarStatusPedidoUseCase.alterarParaEmPreparacao(idPedido);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("pronto/{idPedido}")
    public ResponseEntity<Void> pronto(@PathVariable Long idPedido) {
        alterarStatusPedidoUseCase.alterarParaPronto(idPedido);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("finalizado/{idPedido}")
    public ResponseEntity<Void> finalizado(@PathVariable Long idPedido) {
        alterarStatusPedidoUseCase.alterarParaFinalizado(idPedido);

        return ResponseEntity.noContent().build();
    }
}
