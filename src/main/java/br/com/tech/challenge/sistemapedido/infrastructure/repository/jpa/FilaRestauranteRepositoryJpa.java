package br.com.tech.challenge.sistemapedido.infrastructure.repository.jpa;

import br.com.tech.challenge.sistemapedido.domain.StatusPedido;
import br.com.tech.challenge.sistemapedido.infrastructure.model.FilaRestauranteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilaRestauranteRepositoryJpa extends JpaRepository<FilaRestauranteModel, Long> {

    List<FilaRestauranteModel> findAllByPedidoStatusIn(List<StatusPedido> status);
}
