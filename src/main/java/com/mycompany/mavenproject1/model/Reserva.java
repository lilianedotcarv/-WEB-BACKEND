package com.mycompany.mavenproject1.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reserva {

    private Long id;
    private String nome;
    private String cpf;
    private LocalDate dataChegada;
    private Integer numeroNoites;
    private LocalDate dataSaida;
    private LocalDateTime dataRegistro;

    public Reserva() {
    }

    public Reserva(Long id, String nome, String cpf, LocalDate dataChegada, Integer numeroNoites, LocalDate dataSaida, LocalDateTime dataRegistro) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataChegada = dataChegada;
        this.numeroNoites = numeroNoites;
        this.dataSaida = dataSaida;
        this.dataRegistro = dataRegistro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
}
