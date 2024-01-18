package br.com.tech.challenge.sistemapedido.infrastructure.security;

import br.com.tech.challenge.sistemapedido.domain.Usuario;
import br.com.tech.challenge.sistemapedido.usecase.gateway.UsuarioGateway;
import br.com.tech.challenge.sistemapedido.domain.service.AutenticarUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AutenticarUsuarioServiceImpl implements AutenticarUsuarioService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioGateway usuarioGateway;


    @Override
    public String autenticar(String cpf, String senha) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                cpf, senha));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public Usuario registrar(Usuario usuario) {
        var usuarioComSenhaEncriptada = new Usuario(usuario.getNome(),
                usuario.getCpf(), usuario.getEmail(), passwordEncoder.encode(usuario.getSenha()), usuario.getPapeis());
        return usuarioGateway.salvar(usuarioComSenhaEncriptada);
    }
}
