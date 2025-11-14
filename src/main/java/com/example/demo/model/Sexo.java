package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Sexo {
    MASCULINO("Masculino"), FEMININO("Feminino");

    private String descricao;
}
