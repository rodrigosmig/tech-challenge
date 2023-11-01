package br.com.tech.challenge.sistemapedido.infrastructure.repository.jpa;

import br.com.tech.challenge.sistemapedido.infrastructure.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PedidoRepositoryJpa extends JpaRepository<PedidoModel, Long> {
}
