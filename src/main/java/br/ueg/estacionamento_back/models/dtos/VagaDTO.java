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
public class VagaDTO {

    @Schema(example = "28")
    private int numero;

    @Schema(example = "4")
    private int lococaoHorizontal;

    @Schema(example = "8")
    private int locacaoVertical;
}
