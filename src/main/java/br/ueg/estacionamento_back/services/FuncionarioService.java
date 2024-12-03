package br.ueg.estacionamento_back.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ueg.estacionamento_back.models.FuncionarioModel;
import br.ueg.estacionamento_back.repositories.FuncionarioRepository;
import jakarta.validation.Valid;

@Service
public class FuncionarioService {
    
    @Autowired
    private FuncionarioRepository repository;

    public FuncionarioModel create(@Valid FuncionarioModel funcionarioModel) {
        Optional<FuncionarioModel> existingFuncionario = validateFuncionarioExistsByEmail(funcionarioModel.getEmail());

        if (existingFuncionario.isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        return repository.save(funcionarioModel);
    }

    private Optional<FuncionarioModel> validateFuncionarioExistsByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<FuncionarioModel> getAll() {
        return repository.findAll();
    }

    public Optional<FuncionarioModel> getById(Long id) {
        return validateFuncionarioExists(id);
    }

    public Optional<FuncionarioModel> updateById(Long id, @Valid FuncionarioModel funcionarioUpdate) {
        Optional<FuncionarioModel> funcionarioOpt = validateFuncionarioExists(id);
        if (funcionarioOpt.isPresent()) {
            FuncionarioModel funcionario = funcionarioOpt.get();
            updateDataDB(funcionario, funcionarioUpdate);
            repository.save(funcionario);
            return Optional.of(funcionario);
        }
        return Optional.empty();
    }

    public Optional<FuncionarioModel> deleteById(Long id) {
        Optional<FuncionarioModel> funcionarioOpt = validateFuncionarioExists(id);
        if (funcionarioOpt.isPresent()) {
            FuncionarioModel funcionario = funcionarioOpt.get();
            repository.delete(funcionario);
            return Optional.of(funcionario);
        }
        return Optional.empty();
    }

    public Optional<FuncionarioModel> validateFuncionarioExists(Long id) {
        FuncionarioModel funcionarioModel = null;
        if (Objects.nonNull(id)) {
            funcionarioModel = this.internalGet(id);
        }
        return Optional.ofNullable(funcionarioModel);
    }

    public FuncionarioModel internalGet(Long id) {
        Optional<FuncionarioModel> funcionarioModel = repository.findById(id);
        return funcionarioModel.orElse(null);
    }

    public void updateDataDB(@Valid FuncionarioModel funcionario, @Valid FuncionarioModel funcionarioUpdate) {
        if (Objects.nonNull(funcionarioUpdate.getNome())) {
            funcionario.setNome(funcionarioUpdate.getNome());
        }
        if (Objects.nonNull(funcionarioUpdate.getEmail())) {
            funcionario.setEmail(funcionarioUpdate.getEmail());
        }
        if (Objects.nonNull(funcionarioUpdate.getSenha())) {
            funcionario.setSenha(funcionarioUpdate.getSenha());
        }
    }

    public Optional<FuncionarioModel> login(String email, String senha) {
        Optional<FuncionarioModel> funcionarioOpt = repository.findByEmail(email);
        if (funcionarioOpt.isPresent()) {
            FuncionarioModel funcionario = funcionarioOpt.get();
            if (funcionario.getSenha().equals(senha)) {
                return Optional.of(funcionario);
            }
        }
        return Optional.empty();
    }

}
