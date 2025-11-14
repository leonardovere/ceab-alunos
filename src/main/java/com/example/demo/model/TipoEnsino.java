package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoEnsino {
    FUNDAMENTAL("Fundamental"), MEDIO("Médio"), TECNICO("Técnico");

    private String descricao;
}
