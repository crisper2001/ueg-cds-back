package br.ueg.estacionamento_back.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ueg.estacionamento_back.models.PermanenciaModel;
import br.ueg.estacionamento_back.repositories.PermanenciaRepository;
import jakarta.validation.Valid;

@Service
public class PermanenciaService {
    
    @Autowired
    private PermanenciaRepository repository;

    public PermanenciaModel create(PermanenciaModel permanenciaModel) {
        return repository.save(permanenciaModel);
    }

    public List<PermanenciaModel> getAll() {
        return repository.findAll();
    }

    public Optional<PermanenciaModel> getById(Long id) {
        return repository.findById(id);
    }

    public Optional<PermanenciaModel> updateById(Long id, @Valid PermanenciaModel permanenciaUpdate) {
        Optional<PermanenciaModel> permanenciaOpt = validatePermanenciaExists(id);
        if (permanenciaOpt.isPresent()) {
            PermanenciaModel permanencia = permanenciaOpt.get();
            updateDataDB(permanencia, permanenciaUpdate);
            repository.save(permanencia);
            return Optional.of(permanencia);
        }
        return Optional.empty();
    }

    public Optional<PermanenciaModel> deleteById(Long id) {
        Optional<PermanenciaModel> permanenciaOpt = validatePermanenciaExists(id);
        if (permanenciaOpt.isPresent()) {
            PermanenciaModel permanencia = permanenciaOpt.get();
            repository.delete(permanencia);
            return Optional.of(permanencia);
        }
        return Optional.empty();
    }

    public Optional<PermanenciaModel> validatePermanenciaExists(Long id) {
        PermanenciaModel permanenciaModel = null;
        if (Objects.nonNull(id)) {
            permanenciaModel = this.internalGet(id);
        }
        return Optional.ofNullable(permanenciaModel);
    }

    public PermanenciaModel internalGet(Long id) {
        Optional<PermanenciaModel> permanenciaModel = repository.findById(id);
        return permanenciaModel.orElse(null);
    }

    public void updateDataDB(@Valid PermanenciaModel permanencia, @Valid PermanenciaModel permanenciaUpdate) {
        if (Objects.nonNull(permanenciaUpdate.getDataHoraEntrada())) {
            permanencia.setDataHoraEntrada(permanenciaUpdate.getDataHoraEntrada());
        }
        if (Objects.nonNull(permanenciaUpdate.getDataHoraSaida())) {
            permanencia.setDataHoraSaida(permanenciaUpdate.getDataHoraSaida());
        }
        if (Objects.nonNull(permanenciaUpdate.getPlacaVeiculo())) {
            permanencia.setPlacaVeiculo(permanenciaUpdate.getPlacaVeiculo());
        }
        if (Objects.nonNull(permanenciaUpdate.getValor())) {
            permanencia.setValor(permanenciaUpdate.getValor());
        }
    }

}
