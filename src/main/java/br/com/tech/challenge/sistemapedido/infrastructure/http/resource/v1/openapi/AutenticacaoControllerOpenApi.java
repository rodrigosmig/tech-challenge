package br.com.tech.challenge.sistemapedido.infrastructure.http.resource.v1.openapi;

import br.com.tech.challenge.sistemapedido.application.dto.InputErrorDTO;
import br.com.tech.challenge.sistemapedido.application.request.AutenticarUsuarioRequest;
import br.com.tech.challenge.sistemapedido.application.request.RegistrarUsuarioRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Autenticação", description = "Gerencia a autenticação")
public interface AutenticacaoControllerOpenApi {

    @Operation(summary = "Autenticar um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201"),
            @ApiResponse(responseCode = "422",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = InputErrorDTO.class))}
            )

    })
    ResponseEntity<?> autenticarUsuario(@RequestBody AutenticarUsuarioRequest request);

    @Operation(summary = "Registrar um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201"),
            @ApiResponse(responseCode = "422",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = InputErrorDTO.class))}
            )

    })
    ResponseEntity<?> registrarUsuario(@RequestBody RegistrarUsuarioRequest request);


}
