package br.ueg.estacionamento_back.mappers;

import org.springframework.stereotype.Component;

import br.ueg.estacionamento_back.models.PrecoModel;
import br.ueg.estacionamento_back.models.dtos.PrecoDTO;
import jakarta.validation.Valid;

@Component
public class PrecoMapper {
    
    public PrecoModel toPrecoModel(@Valid PrecoDTO precoDTO) {
        PrecoModel precoModel = new PrecoModel();
        precoModel.setTempoInicial(precoDTO.getTempoInicial());
        precoModel.setTempoAdicional(precoDTO.getTempoAdicional());
        precoModel.setValorInicial(precoDTO.getValorInicial());
        precoModel.setValorAdicional(precoDTO.getValorAdicional());
        return precoModel;
    }

}
