package br.ueg.estacionamento_back.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PrecoDTO {

    @Schema(description = "O tempo inicial em minutos", example = "60", required = true)
    @NotBlank
    private Integer tempoInicial;

    @Schema(description = "O tempo adicional em minutos", example = "30", required = true)
    @NotBlank
    private Integer tempoAdicional;

    @Schema(description = "O valor inicial em reais", example = "0.00", required = true)
    @NotBlank
    private Double valorInicial;

    @Schema(description = "O valor adicional em reais", example = "10.00", required = true)
    @NotBlank
    private Double valorAdicional;
}
