package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Identificacao;
import com.example.demo.model.TipoEnsino;
import com.example.demo.model.Turma;

public interface TurmaRepository extends JpaRepository<Turma, Long> {

    public Optional<Turma> findByAnoAndTipoAndIdentificacao(Integer ano, TipoEnsino tipo, Identificacao identificacao);
}
