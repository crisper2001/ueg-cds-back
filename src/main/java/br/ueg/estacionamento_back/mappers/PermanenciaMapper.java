package br.ueg.estacionamento_back.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ueg.estacionamento_back.models.FuncionarioModel;
import br.ueg.estacionamento_back.models.PermanenciaModel;
import br.ueg.estacionamento_back.models.PrecoModel;
import br.ueg.estacionamento_back.models.VagaModel;
import br.ueg.estacionamento_back.models.dtos.PermanenciaDTO;
import br.ueg.estacionamento_back.services.FuncionarioService;
import br.ueg.estacionamento_back.services.PrecoService;
import br.ueg.estacionamento_back.services.VagaService;

@Component
public class PermanenciaMapper {

    @Autowired
    private VagaService vagaService;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private PrecoService precoService;
    
    public PermanenciaModel toPermanenciaModel(PermanenciaDTO permanenciaDTO) {
        PermanenciaModel permanenciaModel = new PermanenciaModel();
        permanenciaModel.setDataHoraEntrada(permanenciaDTO.getDataHoraEntrada());
        permanenciaModel.setDataHoraSaida(permanenciaDTO.getDataHoraSaida());
        permanenciaModel.setPlacaVeiculo(permanenciaDTO.getPlacaVeiculo());
        VagaModel vaga = vagaService.internalGet(permanenciaDTO.getVagaId());
        FuncionarioModel funcionario = funcionarioService.internalGet(permanenciaDTO.getFuncionarioId());
        PrecoModel preco = precoService.internalGet(permanenciaDTO.getPrecoId());
        permanenciaModel.setVaga(vaga);
        permanenciaModel.setFuncionario(funcionario);
        permanenciaModel.setPreco(preco);
        return permanenciaModel;
    }

}
