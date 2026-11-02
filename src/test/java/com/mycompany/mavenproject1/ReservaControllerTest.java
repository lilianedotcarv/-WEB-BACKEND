package com.mycompany.mavenproject1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.mavenproject1.dto.ReservaRequestDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ReservaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deve criar reserva com sucesso via JSON")
    void deveCriarReservaComSucesso() throws Exception {
        LocalDate dataChegada = LocalDate.now().plusDays(2);
        ReservaRequestDTO dto = new ReservaRequestDTO("Maria Oliveira", "12345678909", dataChegada, 4);

        mockMvc.perform(post("/api/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.nome", is("Maria Oliveira")))
                .andExpect(jsonPath("$.cpf", is("123.456.789-09")))
                .andExpect(jsonPath("$.dataChegada", is(dataChegada.toString())))
                .andExpect(jsonPath("$.numeroNoites", is(4)))
                .andExpect(jsonPath("$.dataSaida", is(dataChegada.plusDays(4).toString())))
                .andExpect(jsonPath("$.mensagem", is("Reserva realizada com sucesso!")));
    }

    @Test
    @DisplayName("Deve rejeitar requisição com campos inválidos ou vazios")
    void deveRejeitarCamposInvalidos() throws Exception {
        ReservaRequestDTO dto = new ReservaRequestDTO("", "123", LocalDate.now().minusDays(5), 0);

        mockMvc.perform(post("/api/reservas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.detalhes.nome", notNullValue()))
                .andExpect(jsonPath("$.detalhes.cpf", notNullValue()))
                .andExpect(jsonPath("$.detalhes.dataChegada", notNullValue()))
                .andExpect(jsonPath("$.detalhes.numeroNoites", notNullValue()));
    }

    @Test
    @DisplayName("Deve receber dados via formulário tradicional (x-www-form-urlencoded)")
    void deveReceberDadosViaFormularioUrlEncoded() throws Exception {
        LocalDate dataChegada = LocalDate.now().plusDays(1);

        mockMvc.perform(post("/api/reservas")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("nome", "Joao Pereira")
                        .param("cpf", "98765432100")
                        .param("dataChegada", dataChegada.toString())
                        .param("numeroNoites", "2"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.nome", is("Joao Pereira")))
                .andExpect(jsonPath("$.numeroNoites", is(2)));
    }

    @Test
    @DisplayName("Deve listar todas as reservas cadastradas")
    void deveListarTodasAsReservas() throws Exception {
        mockMvc.perform(get("/api/reservas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(0))));
    }
}
