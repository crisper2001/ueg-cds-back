package br.ueg.estacionamento_back.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FuncionarioLoginDTO {
    @Schema(description = "O e-mail do funcionário", example = "jefflynne@elo.com")
    @Email
    private String email;

    @Schema(description = "A senha do funcionário", example = "yourstruly2095")
    @Size(min = 8)
    private String senha;
}
