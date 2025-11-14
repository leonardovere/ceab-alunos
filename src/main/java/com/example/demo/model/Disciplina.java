package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "disciplina")
@Data
@NoArgsConstructor
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A turma é obrigatória")
    @ManyToOne
    @JoinColumn(name = "turma_id", nullable = false)
    private Turma turma;

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 255, message = "O nome deve ter no máximo {max} caracteres")
    @Column(nullable = false)
    private String nome;
    
    @NotNull(message = "A carga horária é obrigatória")
    @Column(name = "carga_horaria", nullable = false)
    private Integer cargaHoraria;
}
