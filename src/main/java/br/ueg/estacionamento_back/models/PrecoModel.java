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
@Table(name = "PRECO")
public class PrecoModel implements IGenericModel<Long> {

    @Id
    @SequenceGenerator(
            name = "preco_sequence",
            sequenceName = "preco_sequence_bd",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "preco_sequence"
    )
    private Long id;

    @NotNull
    @Column(name = "Iempo_Inicial", nullable = false)
    private Integer tempoinicial;

    @NotNull
    @Column(name = "Preco-Inicial", nullable = false)
    private Integer precoinicial;

    @NotNull
    @Column(name = "Preco-Adicional", nullable = false)
    private Integer precoadicional;

}
