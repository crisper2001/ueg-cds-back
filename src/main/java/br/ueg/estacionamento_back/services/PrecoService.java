package br.ueg.estacionamento_back.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ueg.estacionamento_back.models.PrecoModel;
import br.ueg.estacionamento_back.repositories.PrecoRepository;
import jakarta.validation.Valid;

@Service
public class PrecoService {

  @Autowired
  private PrecoRepository repository;

  public PrecoModel create(PrecoModel precoModel) {
    return repository.save(precoModel);
  }

  public List<PrecoModel> getAll() {
    return repository.findAll();
  }

  public Optional<PrecoModel> getById(Long id) {
    return repository.findById(id);
  }

  public Optional<PrecoModel> updateById(Long id, @Valid PrecoModel precoUpdate) {
    Optional<PrecoModel> precoOpt = validatePrecoExists(id);
    System.out.println(precoOpt);
    System.out.println(precoUpdate);
    if (precoOpt.isPresent()) {
      PrecoModel preco = precoOpt.get();
      updateDataDB(preco, precoUpdate);
      repository.save(preco);
      return Optional.of(preco);
    }
    return Optional.empty();
  }

  public Optional<PrecoModel> deleteById(Long id) {
    Optional<PrecoModel> precoOpt = validatePrecoExists(id);
    if (precoOpt.isPresent()) {
      PrecoModel preco = precoOpt.get();
      repository.delete(preco);
      return Optional.of(preco);
    }
    return Optional.empty();
  }

  public Optional<PrecoModel> validatePrecoExists(Long id) {
    PrecoModel precoModel = null;
    if (Objects.nonNull(id)) {
      precoModel = this.internalGet(id);
    }
    return Optional.ofNullable(precoModel);
  }

  public PrecoModel internalGet(Long id) {
    Optional<PrecoModel> precoModel = repository.findById(id);
    return precoModel.orElse(null);
  }

  public void updateDataDB(@Valid PrecoModel preco, @Valid PrecoModel precoUpdate) {
    if (Objects.nonNull(precoUpdate.getTempoInicial())) {
      preco.setTempoInicial(precoUpdate.getTempoInicial());
    }
    if (Objects.nonNull(precoUpdate.getTempoAdicional())) {
      preco.setTempoAdicional(precoUpdate.getTempoAdicional());
    }
    if (Objects.nonNull(precoUpdate.getValorInicial())) {
      preco.setValorInicial(precoUpdate.getValorInicial());
    }
    if (Objects.nonNull(precoUpdate.getValorAdicional())) {
      preco.setValorAdicional(precoUpdate.getValorAdicional());
    }
  }

}
