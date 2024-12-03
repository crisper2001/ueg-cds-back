package br.ueg.estacionamento_back.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "PRECO")
public class PrecoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Column(name = "tempo_inicial")
    private Integer tempoInicial;

    @NotNull
    @Column(name = "tempo_adicional")
    private Integer tempoAdicional;

    @NotNull
    @Column(name = "valor_inicial")
    private Double valorInicial;

    @NotNull
    @Column(name = "valor_adicional")
    private Double valorAdicional;

    @JsonManagedReference
    @OneToMany(mappedBy = "preco", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PermanenciaModel> permanencia = new ArrayList<>();

}
