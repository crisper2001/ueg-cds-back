package br.ueg.estacionamento_back.mappers;

import org.springframework.stereotype.Component;

import br.ueg.estacionamento_back.models.FuncionarioModel;
import br.ueg.estacionamento_back.models.dtos.FuncionarioCreateDTO;
import br.ueg.estacionamento_back.models.dtos.FuncionarioLoginDTO;
import br.ueg.estacionamento_back.models.dtos.FuncionarioUpdateDTO;
import jakarta.validation.Valid;

@Component
public class FuncionarioMapper {
    
    public FuncionarioModel toFuncionarioModel(@Valid FuncionarioCreateDTO funcionarioCreateDTO) {
        FuncionarioModel funcionarioModel = new FuncionarioModel();
        funcionarioModel.setNome(funcionarioCreateDTO.getNome());
        funcionarioModel.setEmail(funcionarioCreateDTO.getEmail());
        funcionarioModel.setSenha(funcionarioCreateDTO.getSenha());
        return funcionarioModel;
    }

    public FuncionarioModel toFuncionarioModel(@Valid FuncionarioUpdateDTO funcionarioUpdateDTO) {
        FuncionarioModel funcionarioModel = new FuncionarioModel();
        funcionarioModel.setNome(funcionarioUpdateDTO.getNome());
        funcionarioModel.setEmail(funcionarioUpdateDTO.getEmail());
        funcionarioModel.setSenha(funcionarioUpdateDTO.getSenha());
        return funcionarioModel;
    }

    public FuncionarioModel toFuncionarioModel(@Valid FuncionarioLoginDTO funcionarioLoginDTO) {
        FuncionarioModel funcionarioModel = new FuncionarioModel();
        funcionarioModel.setEmail(funcionarioLoginDTO.getEmail());
        funcionarioModel.setSenha(funcionarioLoginDTO.getSenha());
        return funcionarioModel;
    }

}
