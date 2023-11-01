package br.com.tech.challenge.sistemapedido.infrastructure.security;

import br.com.tech.challenge.sistemapedido.core.service.AutenticarUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AutenticarUsuarioServiceImpl implements AutenticarUsuarioService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public String autenticar(String cpf, String senha) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                cpf, senha));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                cpf, senha));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }
}
