package br.ueg.estacionamento_back.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ueg.estacionamento_back.models.VagaModel;
import br.ueg.estacionamento_back.repositories.VagaRepository;
import jakarta.validation.Valid;

@Service
public class VagaService {
    
    @Autowired
    private VagaRepository repository;

    public VagaModel create(VagaModel vagaModel) {
        return repository.save(vagaModel);
    }

    public List<VagaModel> getAll() {
        return repository.findAll();
    }

    public Optional<VagaModel> getById(Long id) {
        return repository.findById(id);
    }

    public Optional<VagaModel> updateById(Long id, @Valid VagaModel vagaUpdate) {
        Optional<VagaModel> vagaOpt = validateVagaExists(id);
        if (vagaOpt.isPresent()) {
            VagaModel vaga = vagaOpt.get();
            updateDataDB(vaga, vagaUpdate);
            repository.save(vaga);
            return Optional.of(vaga);
        }
        return Optional.empty();
    }

    public Optional<VagaModel> deleteById(Long id) {
        Optional<VagaModel> vagaOpt = validateVagaExists(id);
        if (vagaOpt.isPresent()) {
            VagaModel vaga = vagaOpt.get();
            repository.delete(vaga);
            return Optional.of(vaga);
        }
        return Optional.empty();
    }

    public Optional<VagaModel> validateVagaExists(Long id) {
        VagaModel vagaModel = null;
        if (Objects.nonNull(id)) {
            vagaModel = this.internalGet(id);
        }
        return Optional.ofNullable(vagaModel);
    }

    public VagaModel internalGet(Long id) {
        Optional<VagaModel> vagaModel = repository.findById(id);
        return vagaModel.orElse(null);
    }

    public void updateDataDB(@Valid VagaModel vaga, @Valid VagaModel vagaUpdate) {
        if (Objects.nonNull(vagaUpdate.getNumero())) {
            vaga.setNumero(vagaUpdate.getNumero());
        }
        if (Objects.nonNull(vagaUpdate.getLocHorizontal())) {
            vaga.setLocHorizontal(vagaUpdate.getLocHorizontal());
        }
        if (Objects.nonNull(vagaUpdate.getLocVertical())) {
            vaga.setLocVertical(vagaUpdate.getLocVertical());
        }
    }

}
