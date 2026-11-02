package com.mycompany.mavenproject1.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class ReservaRequestDTO {

    @NotBlank(message = "O campo 'nome' é obrigatório.")
    @Size(min = 2, max = 100, message = "O nome deve conter entre 2 e 100 caracteres.")
    private String nome;

    @NotBlank(message = "O campo 'cpf' é obrigatório.")
    @Pattern(
        regexp = "(^\\d{11}$)|(^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$)",
        message = "O CPF deve ter 11 dígitos (ex: 12345678901) ou o formato 000.000.000-00."
    )
    private String cpf;

    @NotNull(message = "O campo 'data de chegada' é obrigatório.")
    @FutureOrPresent(message = "A data de chegada deve ser a data atual ou uma data futura.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonAlias({"data_chegada", "dataChegada", "data"})
    private LocalDate dataChegada;

    @NotNull(message = "O campo 'número de noites' é obrigatório.")
    @Min(value = 1, message = "O número de noites deve ser no mínimo 1.")
    @Max(value = 365, message = "O número de noites não pode ultrapassar 365.")
    @JsonAlias({"numeroNoites", "numero_noites", "numerosDeNoites", "numeros_de_noites", "noites"})
    private Integer numeroNoites;

    public ReservaRequestDTO() {
    }

    public ReservaRequestDTO(String nome, String cpf, LocalDate dataChegada, Integer numeroNoites) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataChegada = dataChegada;
        this.numeroNoites = numeroNoites;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataChegada() {
        return dataChegada;
    }

    public void setDataChegada(LocalDate dataChegada) {
        this.dataChegada = dataChegada;
    }

    public Integer getNumeroNoites() {
        return numeroNoites;
    }

    public void setNumeroNoites(Integer numeroNoites) {
        this.numeroNoites = numeroNoites;
    }

    @Override
    public String toString() {
        return "ReservaRequestDTO{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataChegada=" + dataChegada +
                ", numeroNoites=" + numeroNoites +
                '}';
    }
}
