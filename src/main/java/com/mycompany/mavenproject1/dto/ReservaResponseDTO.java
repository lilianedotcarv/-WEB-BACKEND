package com.mycompany.mavenproject1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mycompany.mavenproject1.model.Reserva;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservaResponseDTO {

    private Long id;
    private String nome;
    private String cpf;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataChegada;

    private Integer numeroNoites;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataSaida;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataRegistro;

    private String mensagem;

    public ReservaResponseDTO() {
    }

    public ReservaResponseDTO(Long id, String nome, String cpf, LocalDate dataChegada, Integer numeroNoites, LocalDate dataSaida, LocalDateTime dataRegistro, String mensagem) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataChegada = dataChegada;
        this.numeroNoites = numeroNoites;
        this.dataSaida = dataSaida;
        this.dataRegistro = dataRegistro;
        this.mensagem = mensagem;
    }

    public static ReservaResponseDTO fromEntity(Reserva reserva, String mensagem) {
        return new ReservaResponseDTO(
                reserva.getId(),
                reserva.getNome(),
                reserva.getCpf(),
                reserva.getDataChegada(),
                reserva.getNumeroNoites(),
                reserva.getDataSaida(),
                reserva.getDataRegistro(),
                mensagem
        );
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

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
