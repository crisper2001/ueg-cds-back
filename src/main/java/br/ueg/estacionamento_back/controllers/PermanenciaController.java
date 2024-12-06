package br.ueg.estacionamento_back.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ueg.estacionamento_back.mappers.PermanenciaMapper;
import br.ueg.estacionamento_back.models.FuncionarioModel;
import br.ueg.estacionamento_back.models.PermanenciaModel;
import br.ueg.estacionamento_back.models.PrecoModel;
import br.ueg.estacionamento_back.models.VagaModel;
import br.ueg.estacionamento_back.models.dtos.PermanenciaCreateDTO;
import br.ueg.estacionamento_back.models.dtos.PermanenciaUpdateDTO;
import br.ueg.estacionamento_back.services.FuncionarioService;
import br.ueg.estacionamento_back.services.PermanenciaService;
import br.ueg.estacionamento_back.services.PrecoService;
import br.ueg.estacionamento_back.services.VagaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/permanencias")
public class PermanenciaController {

  @Autowired
  private PermanenciaService permanenciaService;

  @Autowired
  private PermanenciaMapper permanenciaMapper;

  @Autowired
  private VagaService vagaService;

  @Autowired
  private FuncionarioService funcionarioService;

  @Autowired
  private PrecoService precoService;

  @PostMapping
  @Operation(description = "Endpoint para adicionar uma permanência")
  public ResponseEntity<Object> create(@Valid @RequestBody PermanenciaCreateDTO permanenciaDTO) {
    try {
      Optional<FuncionarioModel> funcionario = funcionarioService
          .validateFuncionarioExists(permanenciaDTO.getFuncionarioId());
      Optional<VagaModel> vaga = vagaService.validateVagaExists(permanenciaDTO.getVagaId());
      Optional<PrecoModel> preco = precoService.validatePrecoExists(permanenciaDTO.getPrecoId());
      if (funcionario.isEmpty() || vaga.isEmpty() || preco.isEmpty()) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
      }
      PermanenciaModel permanencia = permanenciaService.create(permanenciaMapper.toPermanenciaModel(permanenciaDTO));
      return ResponseEntity.status(HttpStatus.CREATED).body(permanencia);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }

  @GetMapping
  @Operation(description = "Endpoint para listar todas as permanências")
  public ResponseEntity<Object> getAll() {
    try {
      List<PermanenciaModel> permanencias = permanenciaService.getAll();
      if (permanencias == null || permanencias.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
      }
      return ResponseEntity.ok(permanencias);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  @GetMapping("/{id}")
  @Operation(description = "Endpoint para exibir os dados de uma permanência pelo ID")
  public ResponseEntity<Object> getById(@PathVariable Long id) {
    try {
      Optional<PermanenciaModel> permanencia = permanenciaService.getById(id);
      if (permanencia.isPresent()) {
        return ResponseEntity.ok(permanencia);
      } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  @PutMapping("/{id}")
  @Operation(description = "Endpoint para atualizar os dados de uma permanência pelo ID")
  public ResponseEntity<Object> updateById(@PathVariable Long id,
      @Valid @RequestBody PermanenciaUpdateDTO permanenciaDTO) {
    try {
      Optional<PermanenciaModel> permanencia = permanenciaService.updateById(id,
          permanenciaMapper.toPermanenciaModel(permanenciaDTO));
      if (permanencia.isPresent()) {
        return ResponseEntity.ok(permanencia);
      } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  @DeleteMapping("/{id}")
  @Operation(description = "Endpoint para deletar uma permanência pelo ID")
  public ResponseEntity<Object> deleteById(@PathVariable("id") Long id) {
    try {
      Optional<PermanenciaModel> permanencia = permanenciaService.deleteById(id);
      if (permanencia.isPresent()) {
        return ResponseEntity.ok(permanencia);
      } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
      }
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

}
