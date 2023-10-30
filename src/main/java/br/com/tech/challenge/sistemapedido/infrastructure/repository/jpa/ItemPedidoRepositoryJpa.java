package br.com.tech.challenge.sistemapedido.infrastructure.repository.jpa;

import br.com.tech.challenge.sistemapedido.infrastructure.model.ItemPedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepositoryJpa extends JpaRepository<ItemPedidoModel, Long> {
}
