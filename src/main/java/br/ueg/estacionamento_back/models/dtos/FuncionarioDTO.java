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
public class FuncionarioDTO {

    @Schema(example = "Pedro de Alcântara")
    private String nomeCompleto;

    @Schema(example = "dompedro2")
    private String nomeUsuario;

    @Schema(example = "senhasupersecretadodompedro2")
    private String senha;
}
