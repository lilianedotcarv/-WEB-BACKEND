package com.mycompany.mavenproject1.service;

import com.mycompany.mavenproject1.dto.ReservaRequestDTO;
import com.mycompany.mavenproject1.dto.ReservaResponseDTO;
import com.mycompany.mavenproject1.model.Reserva;
import com.mycompany.mavenproject1.repository.ReservaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    private static final Logger log = LoggerFactory.getLogger(ReservaService.class);

    private final ReservaRepository repository;

    public ReservaService(ReservaRepository repository) {
        this.repository = repository;
    }

    public ReservaResponseDTO criarReserva(ReservaRequestDTO dto) {
        log.info("Processando reserva para o hóspede: {} com CPF: {}", dto.getNome(), formatarCpf(dto.getCpf()));

        String cpfLimpo = sanitizarCpf(dto.getCpf());
        LocalDate dataSaida = dto.getDataChegada().plusDays(dto.getNumeroNoites());

        Reserva reserva = new Reserva();
        reserva.setNome(dto.getNome().trim());
        reserva.setCpf(formatarCpf(cpfLimpo));
        reserva.setDataChegada(dto.getDataChegada());
        reserva.setNumeroNoites(dto.getNumeroNoites());
        reserva.setDataSaida(dataSaida);
        reserva.setDataRegistro(LocalDateTime.now());

        Reserva salva = repository.save(reserva);
        log.info("Reserva #{} criada com sucesso! Saída prevista para: {}", salva.getId(), salva.getDataSaida());

        return ReservaResponseDTO.fromEntity(salva, "Reserva realizada com sucesso!");
    }

    public List<ReservaResponseDTO> listarTodas() {
        return repository.findAll().stream()
                .map(r -> ReservaResponseDTO.fromEntity(r, "Consulta realizada"))
                .toList();
    }

    public Optional<ReservaResponseDTO> buscarPorId(Long id) {
        return repository.findById(id)
                .map(r -> ReservaResponseDTO.fromEntity(r, "Reserva encontrada"));
    }

    private String sanitizarCpf(String cpf) {
        if (cpf == null) {
            return "";
        }
        return cpf.replaceAll("\\D", "");
    }

    private String formatarCpf(String cpfNumeros) {
        String apenasDigitos = sanitizarCpf(cpfNumeros);
        if (apenasDigitos.length() == 11) {
            return apenasDigitos.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        }
        return cpfNumeros;
    }
}
