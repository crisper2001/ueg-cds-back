package br.ueg.estacionamento_back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ueg.estacionamento_back.models.VagaModel;

public interface VagaRepository extends JpaRepository<VagaModel, Long> {
    
}
