package br.com.tech.challenge.sistemapedido.infrastructure.repository;

import br.com.tech.challenge.sistemapedido.core.domain.Papel;
import br.com.tech.challenge.sistemapedido.usecase.repository.PapelGateway;
import br.com.tech.challenge.sistemapedido.infrastructure.mapper.PapelModelMapper;
import br.com.tech.challenge.sistemapedido.infrastructure.repository.jpa.PapelRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PapelGatewayImpl implements PapelGateway {
    private final PapelRepositoryJpa repository;
    private final PapelModelMapper papelMapper;

    @Override
    public Optional<Papel> buscarPorId(Long id) {
        return repository.findById(id)
                .map(papelMapper::toDomain);
    }

    @Override
    public List<Papel> buscarTodos() {
        return repository.findAll().stream()
                .map(papelMapper::toDomain)
                .toList();
    }

    @Override
    public Papel salvar(Papel papel) {
        var papelModel = repository.save(papelMapper.toModel(papel));

        return papelMapper.toDomain(papelModel);
    }

    @Override
    public void excluir(Papel papel) {
        var papelModel = papelMapper.toModel(papel);
        repository.delete(papelModel);
    }

    @Override
    public Optional<Papel> buscarPorNome(String nome) {
        return repository.findByNome(nome)
                .map(papelMapper::toDomain);
    }
}
