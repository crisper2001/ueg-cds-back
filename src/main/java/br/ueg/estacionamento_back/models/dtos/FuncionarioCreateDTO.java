package br.ueg.estacionamento_back.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class FuncionarioCreateDTO {

    @Schema(description = "O nome do funcionário", example = "Jeff Lynne", required = true)
    @NotBlank(message = "O nome do funcionário é obrigatório")
    @Size(max = 50, message = "O nome do funcionário não pode ter mais que 100 caracteres")
    private String nome;

    @Schema(description = "O e-mail do funcionário", example = "jefflynne@elo.com", required = true)
    @NotBlank(message = "O e-mail do funcionário é obrigatório")
    @Email(message = "O e-mail do funcionário deve ser válido")
    private String email;

    @Schema(description = "A senha do funcionário", example = "yourstruly2095", required = true)
    @NotBlank(message = "A senha do funcionário é obrigatória")
    @Size(min = 8, message = "A senha do funcionário deve ter no mínimo 8 caracteres")
    private String senha;

}