package com.example.demo.model;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "aluno", uniqueConstraints = @UniqueConstraint(columnNames = { "email", "cpf"}))
@Data
@NoArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A turma é obrigatória")
    @ManyToOne
    @JoinColumn(name = "turma_id", nullable = false)
    private Turma turma;

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 100, message = "A observação deve ter no máximo {max} caracteres")
    @Column(length = 100, nullable = false)
    private String nome;

    @NotNull(message = "O sexo é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 9)
    private Sexo sexo;

    @Past(message = "A data de nascimento deve ser menor que hoje")
    @DateTimeFormat(iso = ISO.DATE)
    @NotNull(message = "A data de nascimento é obrigatória")
    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Email(message = "Não é um e-mail válido")
    @NotBlank(message = "O e-mail é obrigatório")
    @Size(max = 50, message = "O e-mail deve ter no máximo {max} caracteres")
    @Column(length = 50, nullable = true, unique = true)
    private String email;

    @NotBlank(message = "O celular é obrigatório")
    @Size(max = 15, message = "O celular deve ter no máximo {max} caracteres")
    @Column(length = 15, nullable = false)
    private String celular;

    @Size(min = 11, max = 14, message = "O CPF deve ter entre {min} e {max} caracteres")
    @Column(length = 14, unique = true)
    private String cpf;

    @Transient
    public Integer getIdade() {
        return this.dataNascimento == null
                ? 0
                : Period.between(this.dataNascimento, LocalDate.now()).getYears();
    }
}
