package br.ueg.estacionamento_back.models;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "PERMANENCIA")
public class PermanenciaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Column(name = "data_hora_entrada", nullable = false)
    private LocalDateTime dataHoraEntrada;

    @Column(name = "data_hora_saida")
    private LocalDateTime dataHoraSaida;

    @NotNull
    @Column(name = "placa_veiculo", nullable = false)
    private String placaVeiculo;

    @Column(name = "valor")
    private Double valor;

    @JsonBackReference
    @NotNull
    @ManyToOne
    @JoinColumn(name = "vaga_id", updatable = false, nullable = false)
    private VagaModel vaga;

    @JsonBackReference
    @NotNull
    @ManyToOne
    @JoinColumn(name = "funcionario_id", updatable = false, nullable = false)
    private FuncionarioModel funcionario;

    @JsonBackReference
    @NotNull
    @ManyToOne
    @JoinColumn(name = "preco_id", updatable = false, nullable = false)
    private PrecoModel preco;

    public Double getValor() {
        LocalDateTime now = LocalDateTime.now();
        long minutes = java.time.Duration.between(dataHoraEntrada, dataHoraSaida == null ? now : dataHoraSaida).toMinutes();
        if (minutes <= preco.getTempoInicial()) {
            return preco.getValorInicial();
        } else {
            long additionalIntervals = (minutes - preco.getTempoInicial() + preco.getTempoAdicional() - 1) / preco.getTempoAdicional();
            return preco.getValorInicial() + (additionalIntervals * preco.getValorAdicional());
        }
    }

}
