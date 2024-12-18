package br.ueg.estacionamento_back.models.dtos;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PermanenciaCreateDTO {
    
    @Schema(description = "A data e hora de entrada", example = "2024-01-01T12:00:00", required = true)
    private LocalDateTime dataHoraEntrada;

    @NotNull
    @Schema(description = "A data e hora de saída", example = "2024-01-01T13:00:00")
    private LocalDateTime dataHoraSaida;

    @Schema(description = "A placa do veículo", example = "ABC1234", required = true)
    @NotNull
    private String placaVeiculo;

    @Schema(description = "O ID da vaga", example = "1", required = true)
    @NotNull
    private Long vagaId;

    @Schema(description = "O ID do funcionário", example = "1", required = true)
    @NotNull
    private Long funcionarioId;

    @Schema(description = "O ID do preço", example = "1", required = true)
    @NotNull
    private Long precoId;
}
