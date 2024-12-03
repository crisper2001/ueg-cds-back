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

import br.ueg.estacionamento_back.mappers.PrecoMapper;
import br.ueg.estacionamento_back.models.PrecoModel;
import br.ueg.estacionamento_back.models.dtos.PrecoDTO;
import br.ueg.estacionamento_back.services.PrecoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/precos")
public class PrecoController {
    
    @Autowired
    private PrecoService precoService;

    @Autowired
    private PrecoMapper precoMapper;

    @PostMapping
    @Operation(description = "Endpoint para adicionar um preço")
    public ResponseEntity<Object> create(@Valid @RequestBody PrecoDTO precoDTO) {
        try {
            PrecoModel preco = precoService.create(precoMapper.toPrecoModel(precoDTO));
            return ResponseEntity.status(HttpStatus.CREATED).body(preco);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    @Operation(description = "Endpoint para listar todos os preços")
    public ResponseEntity<Object> getAll() {
        try {
            List<PrecoModel> precos = precoService.getAll();
            if (precos == null || precos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
            }
            return ResponseEntity.ok(precos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(description = "Endpoint para exibir os dados de um preço pelo ID")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        try {
            Optional<PrecoModel> preco = precoService.getById(id);
            if (preco.isPresent()) {
                return ResponseEntity.ok(preco);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(description = "Endpoint para atualizar os dados de um preço pelo ID")
    public ResponseEntity<Object> updateById(@PathVariable Long id, @Valid @RequestBody PrecoDTO precoDTO) {
        try {
            Optional<PrecoModel> preco = precoService.updateById(id, precoMapper.toPrecoModel(precoDTO));
            if (preco.isPresent()) {
                return ResponseEntity.ok(preco);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Endpoint para deletar um preço pelo ID")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Long id) {
        try {
            Optional<PrecoModel> preco = precoService.deleteById(id);
            if (preco.isPresent()) {
                return ResponseEntity.ok(preco);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}

