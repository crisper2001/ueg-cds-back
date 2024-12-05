package br.ueg.estacionamento_back.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VagaDTO {

    @Schema(description = "O número da vaga", example = "1", required = true)
    @NotBlank
    private Integer numero;
    
    @Schema(description = "A localização horizontal da vaga", example = "1", required = true)
    @NotBlank
    private Integer locHorizontal;

    @Schema(description = "A localização vertical da vaga", example = "1", required = true)
    @NotBlank
    private Integer locVertical;
    
}
