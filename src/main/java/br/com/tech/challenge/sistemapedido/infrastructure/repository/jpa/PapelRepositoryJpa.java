package br.com.tech.challenge.sistemapedido.infrastructure.repository.jpa;

import br.com.tech.challenge.sistemapedido.infrastructure.model.PapelModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PapelRepositoryJpa extends JpaRepository<PapelModel, Long> {
    Optional<PapelModel> findByNome(String nome);
}
