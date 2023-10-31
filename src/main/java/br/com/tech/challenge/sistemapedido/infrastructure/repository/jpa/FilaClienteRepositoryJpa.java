package br.com.tech.challenge.sistemapedido.infrastructure.repository.jpa;

import br.com.tech.challenge.sistemapedido.core.domain.StatusPedido;
import br.com.tech.challenge.sistemapedido.infrastructure.model.FilaClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilaClienteRepositoryJpa extends JpaRepository<FilaClienteModel, Long> {
    List<FilaClienteModel> findAllByPedidoStatusIn(List<StatusPedido> status);
}
