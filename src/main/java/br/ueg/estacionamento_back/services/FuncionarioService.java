package br.ueg.estacionamento_back.services;

import br.ueg.estacionamento_back.exceptions.CustomException;
import br.ueg.estacionamento_back.models.FuncionarioModel;
import br.ueg.estacionamento_back.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FuncionarioService implements IGenericService<FuncionarioModel> {

    @Autowired
    private FuncionarioRepository repository;

    @Override
    public FuncionarioModel create(FuncionarioModel f) {
        f.setId(null);
        validateBusinessLogic(f);
        return repository.save(f);
    }

    @Override
    public List<FuncionarioModel> getAll() {
        return repository.findAll();
    }

    @Override
    public FuncionarioModel getById(long id) {
        return validateIdExists(id);
    }

    @Override
    public FuncionarioModel updateById(FuncionarioModel fUpdate) {
        FuncionarioModel f = validateIdExists(fUpdate.getId());
        validateBusinessLogic(fUpdate);
        updateDataDB(f, fUpdate);
        return repository.save(f);
    }

    @Override
    public FuncionarioModel deleteById(long id) {
        FuncionarioModel f = validateIdExists(id);
        repository.delete(f);
        return f;
    }

    @Override
    public void validateBusinessLogic(FuncionarioModel f) {
        // Verifica se o nome de usuário já existe
        Optional<FuncionarioModel> existingFuncionario = repository.findByNomeUsuario(f.getNomeUsuario());
        if (existingFuncionario.isPresent() && !existingFuncionario.get().getId().equals(f.getId())) {
            throw new CustomException("ERRO: Já existe um funcionário com o nome de usuário informado.");
        }
    }

    @Override
    public FuncionarioModel validateIdExists(long id) {
        FuncionarioModel f = null;
        if (Objects.nonNull(id)) {
            f = this.internalGet(id);
        }
        if (f == null) {
            throw new CustomException("ERRO: Não existe um funcionário com o ID informado.");
        }
        return f;
    }

    @Override
    public FuncionarioModel internalGet(long id) {
        Optional<FuncionarioModel> f = repository.findById(id);
        return f.orElse(null);
    }

    @Override
    public void updateDataDB(FuncionarioModel f, FuncionarioModel fUpdate) {
        f.setNomeCompleto(fUpdate.getNomeCompleto());
        f.setNomeUsuario(fUpdate.getNomeUsuario());
        f.setSenha(fUpdate.getSenha());
    }
}
