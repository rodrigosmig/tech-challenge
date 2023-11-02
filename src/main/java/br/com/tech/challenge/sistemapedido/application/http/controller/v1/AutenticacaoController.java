package br.com.tech.challenge.sistemapedido.application.http.controller.v1;

import br.com.tech.challenge.sistemapedido.application.http.controller.v1.openapi.AutenticacaoControllerOpenApi;
import br.com.tech.challenge.sistemapedido.application.http.controller.v1.request.AutenticarUsuarioRequest;
import br.com.tech.challenge.sistemapedido.application.http.controller.v1.request.RegistrarUsuarioRequest;
import br.com.tech.challenge.sistemapedido.application.http.controller.v1.response.AutenticarUsuarioResponse;
import br.com.tech.challenge.sistemapedido.application.http.controller.v1.response.RegistrarUsuarioResponse;
import br.com.tech.challenge.sistemapedido.application.http.mapper.UsuarioDataMapper;
import br.com.tech.challenge.sistemapedido.core.usecase.usuario.AutenticarUsuarioUseCase;
import br.com.tech.challenge.sistemapedido.core.usecase.usuario.RegistrarUsuarioUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/autenticacao")
public class AutenticacaoController implements AutenticacaoControllerOpenApi {
    private final UsuarioDataMapper usuarioMapper;
    private final AutenticarUsuarioUseCase autenticarUsuarioUseCase;
    private final RegistrarUsuarioUseCase registrarUsuarioUseCase;

    @PostMapping("/autenticar")
    public ResponseEntity<?> autenticarUsuario(@RequestBody AutenticarUsuarioRequest request) {
        String token = autenticarUsuarioUseCase.autenticar(request.cpf(), request.senha());

        AutenticarUsuarioResponse autenticarUsuarioResponse = new AutenticarUsuarioResponse(token);

        return new ResponseEntity<>(autenticarUsuarioResponse, HttpStatus.OK);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistrarUsuarioRequest request) {
        var usuario = registrarUsuarioUseCase.registrar(usuarioMapper.toDomain(request));

        var resposta = new RegistrarUsuarioResponse(usuario.getId());

        return new ResponseEntity<>(resposta, HttpStatus.CREATED);
    }

}
