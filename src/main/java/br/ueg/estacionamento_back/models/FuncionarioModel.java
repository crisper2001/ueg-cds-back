package br.ueg.estacionamento_back.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FUNCIONARIO")
public class FuncionarioModel implements IGenericModel<Long> {

    @Id
    @SequenceGenerator(
            name = "funcionario_sequence",
            sequenceName = "funcionario_sequence_bd",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "funcionario_sequence"
    )
    private Long id;

    @NotNull
    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;

    @NotNull
    @Column(name = "nome_usuario", nullable = false)
    private String nomeUsuario;

    @NotNull
    @Column(name = "senha", nullable = false)
    private String senha;

}
