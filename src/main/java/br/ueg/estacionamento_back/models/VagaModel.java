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
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "VAGA")
public class VagaModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  @NotBlank
  @Column(name = "numero")
  private Integer numero;

  @NotBlank
  @Column(name = "localizacao_horizontal")
  private Integer locHorizontal;

  @NotBlank
  @Column(name = "localizacao_vertical")
  private Integer locVertical;

  @NotBlank
  @Column(name = "ocupacada")
  private Boolean isOcupada;

  @JsonManagedReference
  @OneToMany(mappedBy = "vaga", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<PermanenciaModel> permanencia = new ArrayList<>();

  public Boolean getIsOcupada() {
    return permanencia.stream()
        .anyMatch(p -> p.getDataHoraSaida() == null);
  }

}
