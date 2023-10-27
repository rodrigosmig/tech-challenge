package br.com.tech.challenge.sistemapedido.infrastructure.repository.jpa;

import br.com.tech.challenge.sistemapedido.infrastructure.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepositoryJpa extends JpaRepository<ProdutoModel, Long> {
}
