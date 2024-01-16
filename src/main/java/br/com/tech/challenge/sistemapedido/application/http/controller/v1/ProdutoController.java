package br.com.tech.challenge.sistemapedido.application.http.controller.v1;

import br.com.tech.challenge.sistemapedido.application.http.controller.v1.openapi.ProdutoControllerOpenApi;
import br.com.tech.challenge.sistemapedido.application.http.controller.v1.request.ProdutoRequest;
import br.com.tech.challenge.sistemapedido.application.http.controller.v1.response.CadastrarProdutoResponse;
import br.com.tech.challenge.sistemapedido.application.http.controller.v1.response.ListarProdutosResponse;
import br.com.tech.challenge.sistemapedido.application.http.controller.v1.response.ProdutoResponse;
import br.com.tech.challenge.sistemapedido.application.http.mapper.ProdutoDataMapper;
import br.com.tech.challenge.sistemapedido.usecase.contract.produto.AlterarProdutoUseCase;
import br.com.tech.challenge.sistemapedido.usecase.contract.produto.BuscarProdutoUseCase;
import br.com.tech.challenge.sistemapedido.usecase.contract.produto.CadastrarProdutoUseCase;
import br.com.tech.challenge.sistemapedido.usecase.contract.produto.ExcluirProdutoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/produtos")
public class ProdutoController implements ProdutoControllerOpenApi {
    private final BuscarProdutoUseCase buscarProdutoUseCase;
    private final CadastrarProdutoUseCase cadastrarProdutoUseCase;
    private final AlterarProdutoUseCase alterarProdutoUseCase;
    private final ExcluirProdutoUseCase excluirProdutoUseCase;
    private final ProdutoDataMapper produtoMapper;

    @GetMapping
    public ResponseEntity<ListarProdutosResponse> listar() {
        var produtos = buscarProdutoUseCase.buscarTodos();
        var resposta = new ListarProdutosResponse(produtoMapper.toList(produtos));

        return ResponseEntity.ok(resposta);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProdutoResponse> buscar(@PathVariable Long id) {
        var produto = buscarProdutoUseCase.buscarPorId(id);

        var resposta = new ProdutoResponse(produtoMapper.toDTO(produto));

        return ResponseEntity.ok(resposta);
    }

    @PostMapping
    public ResponseEntity<CadastrarProdutoResponse> criar(@RequestBody ProdutoRequest request) {
        var produto = cadastrarProdutoUseCase.cadastrar(produtoMapper.toDomain(request));

        var resposta = new CadastrarProdutoResponse(produto.getId());

        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProdutoResponse> alterar(@PathVariable Long id, @RequestBody ProdutoRequest request) {
        var produto = alterarProdutoUseCase.alterar(id, produtoMapper.toDomain(request));

        var resposta = new ProdutoResponse(produtoMapper.toDTO(produto));

        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        excluirProdutoUseCase.excluir(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
