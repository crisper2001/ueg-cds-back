package br.ueg.estacionamento_back.mappers;

import br.ueg.estacionamento_back.models.VagaModel;
import br.ueg.estacionamento_back.models.dtos.VagaDTO;
import org.springframework.stereotype.Component;

@Component
public class VagaMapper {

    public VagaModel toVagaModel(VagaDTO vagaDTO) {
        VagaModel vagaModel = new VagaModel();
        vagaModel.setNumero(vagaDTO.getNumero());
        vagaModel.setLocHorizontal(vagaDTO.getLocHorizontal());
        vagaModel.setLocVertical(vagaDTO.getLocVertical());
        return vagaModel;
    }
    
}
