package br.com.tech.challenge.sistemapedido.infrastructure.repository.jpa;

import br.com.tech.challenge.sistemapedido.infrastructure.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepositoryJpa extends JpaRepository<UsuarioModel, Long> {
    Optional<UsuarioModel> findByEmail(String email);

    Optional<UsuarioModel> findByCpfOrEmail(String cpf, String email);

    Optional<UsuarioModel> findByCpf(String cpf);

    Boolean existsByCpf(String cpf);

    Boolean existsByEmail(String email);
}
