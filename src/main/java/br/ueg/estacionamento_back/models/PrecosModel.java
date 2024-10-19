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
@Table(name = "PRECOS")
public class PrecosModel implements IGenericModel<Long> {

    @Id
    @SequenceGenerator(
            name = "precos_sequence",
            sequenceName = "precos_sequence_bd",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "precos_sequence"
    )
    private Long id;

    @NotNull
    @Column(name = "Iempo_Inicial", nullable = false)
    private Integer tempoInicial;

    @NotNull
    @Column(name = "Precos-Inicial", nullable = false)
    private float precosInicial;

    @NotNull
    @Column(name = "Precos-Adicional", nullable = false)
    private float precosAdicional;

}
