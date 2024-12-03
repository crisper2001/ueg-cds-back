package br.ueg.estacionamento_back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ueg.estacionamento_back.models.PrecoModel;

public interface PrecoRepository extends JpaRepository<PrecoModel, Long> {
    
}
