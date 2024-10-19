package br.ueg.estacionamento_back.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PrecosDTO {

    @Schema(example = "99" )
    private int tempoInicial;

    @Schema(example = "2.50")
    private float precosInicial;

    @Schema(example = "44.00")
    private float precosAdicional;
}
