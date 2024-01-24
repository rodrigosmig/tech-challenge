package br.com.tech.challenge.sistemapedido.infrastructure.persistence.jpa;

import br.com.tech.challenge.sistemapedido.infrastructure.persistence.model.ItemPedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepositoryJpa extends JpaRepository<ItemPedidoModel, Long> {
}
