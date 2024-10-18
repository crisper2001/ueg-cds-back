package br.ueg.estacionamento_back.controllers;

import br.ueg.estacionamento_back.mappers.GenericMapper;
import br.ueg.estacionamento_back.models.FuncionarioModel;
import br.ueg.estacionamento_back.models.dtos.FuncionarioDTO;
import br.ueg.estacionamento_back.services.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "${api.version}/estacionamento-back/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @Autowired
    private GenericMapper mapper;

    @PostMapping
    @Operation(description = "Endpoint para adiconar novos funcionários.")
    public ResponseEntity<Object> create(@Valid @RequestBody FuncionarioDTO dto) {
        try {
            FuncionarioModel f = service.create(mapper.map(dto, FuncionarioModel.class)); //Uso da classe com reflexão aqui!
            return ResponseEntity.ok(f);
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().startsWith("ERRO:")) {
                return ResponseEntity.badRequest().body(e.getMessage());
            } else {
                return errorMessage(e);
            }
        }
    }

    @GetMapping
    @Operation(description = "Endpoint para listar todos os funcionários.")
    public ResponseEntity<Object> getAll() {
        try {
            return ResponseEntity.of(Optional.ofNullable(service.getAll()));
        } catch (Exception e) {
            return errorMessage(e);
        }
    }

    @GetMapping(path = "/{id}")
    @Operation(description = "Endpoint para procurar um funcionário pelo seu ID.")
    public ResponseEntity<Object> getById(@PathVariable("id") long id) {
        try {
            return ResponseEntity.of(Optional.ofNullable(service.getById(id)));
        } catch (Exception e) {
            return errorMessage(e);
        }
    }

    @PutMapping(path = "/{id}")
    @Operation(description = "Endpoint para atualizar os dados de um funcionário a partir de seu ID.")
    public ResponseEntity<Object> updateById(
            @PathVariable("id") long id,
            @Valid @RequestBody FuncionarioDTO dto) {
        try {
            FuncionarioModel f = mapper.map(dto, FuncionarioModel.class); // Uso da classe com reflexão aqui!
            f.setId(id);
            return ResponseEntity.of(Optional.ofNullable(service.updateById(f)));
        } catch (Exception e) {
            return errorMessage(e);
        }
    }

    @DeleteMapping(path = "/{id}")
    @Operation(description = "Endpoint para apagar um funcionário a partir de seu ID.")
    public ResponseEntity<Object> deleteById(@PathVariable("id") long id) {
        try {
            return ResponseEntity.of(Optional.ofNullable(service.deleteById(id)));
        } catch (Exception e) {
            return errorMessage(e);
        }
    }

    public ResponseEntity<Object> errorMessage(Exception e) {
        if (e.getMessage().startsWith("ERRO: Não") || e.getMessage().startsWith("Erro: Um funcionário")) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } else {
            return ResponseEntity.internalServerError().body("ERRO: Occorreu um problema no servidor.");
        }
    }

}
