package br.ueg.estacionamento_back.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import br.ueg.estacionamento_back.mappers.FuncionarioMapper;
import br.ueg.estacionamento_back.models.FuncionarioModel;
import br.ueg.estacionamento_back.models.dtos.FuncionarioCreateDTO;
import br.ueg.estacionamento_back.models.dtos.FuncionarioLoginDTO;
import br.ueg.estacionamento_back.models.dtos.FuncionarioUpdateDTO;
import br.ueg.estacionamento_back.services.FuncionarioService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private FuncionarioMapper funcionarioMapper;

    @PostMapping
    @Operation(description = "Endpoint para adicionar um funcionário")
    public ResponseEntity<Object> create(@Valid @RequestBody FuncionarioCreateDTO funcionarioCreateDTO) {
        try {
            FuncionarioModel funcionario = funcionarioService.create(funcionarioMapper.toFuncionarioModel(funcionarioCreateDTO));
            return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    @Operation(description = "Endpoint para listar todos os funcionários")
    public ResponseEntity<Object> getAll() {
        try {
            List<FuncionarioModel> funcionarios = funcionarioService.getAll();
            if (funcionarios == null || funcionarios.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
            }
            return ResponseEntity.ok(funcionarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @Operation(description = "Endpoint para exibir os dados de um funcionário pelo ID")
    public ResponseEntity<Object> getById(@PathVariable Long id) {
        try {
            Optional<FuncionarioModel> funcionario = funcionarioService.getById(id);
            if (funcionario.isPresent()) {
                return ResponseEntity.ok(funcionario);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(description = "Endpoint para atualizar os dados de um funcionário pelo ID")
    public ResponseEntity<Object> updateById(@PathVariable Long id, @Valid @RequestBody FuncionarioUpdateDTO funcionarioUpdateDTO) {
        try {
            Optional<FuncionarioModel> funcionario = funcionarioService.updateById(id, funcionarioMapper.toFuncionarioModel(funcionarioUpdateDTO));
            if (funcionario.isPresent()) {
                return ResponseEntity.ok(funcionario);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Endpoint para deletar um funcionário pelo ID")
    public ResponseEntity<Object> deleteById(@PathVariable("id") long id) {
        try {
            Optional<FuncionarioModel> funcionario = funcionarioService.deleteById(id);
            if (funcionario.isPresent()) {
                return ResponseEntity.ok(funcionario);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    @Operation(description = "Endpoint para fazer login")
    public ResponseEntity<Object> login(@Valid @RequestBody FuncionarioLoginDTO funcionarioLoginDTO) {
        try {
            Optional<FuncionarioModel> funcionario = funcionarioService.login(funcionarioLoginDTO.getEmail(), funcionarioLoginDTO.getSenha());
            if (funcionario.isPresent()) {
                return ResponseEntity.ok(funcionario);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
