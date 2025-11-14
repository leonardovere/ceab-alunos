package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "turma")
@Data
@NoArgsConstructor
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O ano é obrigatório")
    @Min(value = 1, message = "O ano deve ser maior ou igual a {value}")
    @Max(value = 9, message = "O ano deve ser menor ou igual a {value}")
    @Column(nullable = false, length = 2)
    private Integer ano;

    @NotNull(message = "O tipo é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 11)
    private TipoEnsino tipo;
    
    @NotNull(message = "A identificação é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 1)
    private Identificacao identificacao;

    @Size(max = 255, message = "A observação deve ter no máximo {max} caracteres")
    @Column(length = 255)
    private String observacao;

    @Transient
    public String getDescricao() {
        return this.ano + "° " + this.identificacao + " - " + this.tipo.getDescricao();
    }
}
