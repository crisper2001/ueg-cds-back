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
@Table(name = "VAGA")
public class VagaModel implements IGenericModel<Long> {

    @Id
    @SequenceGenerator(
            name = "vaga_sequence",
            sequenceName = "vaga_sequence_bd",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "vaga_sequence"
    )
    private Long id;

    @NotNull
    @Column(name = "numero", nullable = false)
    private int numero;

    @NotNull
    @Column(name = "loc_horizontal", nullable = false)
    private int locHorizontal;

    @NotNull
    @Column(name = "loc_vertical", nullable = false)
    private int locVertical;

}
