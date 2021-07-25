package com.builders.clientshandler.clients.controller;

import com.builders.clientshandler.model.dto.ClientDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClientControllerTest {

    private static final String API = "/v1/api/clients";

    private static final String JSON_CONTENT_TYPE = "application/json";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private ClientDTO clientDTO;

    @BeforeAll
    public void setup() {
        log.info("--- STARTING TESTS ON CONTROLLER ---");

        clientDTO = ClientDTO.builder()
                .id(9999999L)
                .cep("19020-160")
                .cpf("98485503058")
                .cidade("Osasco")
                .estado("SP")
                .dataNascimento(new Date())
                .email("diego56469598746521463257@gmail.com")
                .endereco("Rua Euclides Segundo")
                .nome("Diego Fagundes").build();
    }

    @Order(1)
    @Test
    @Rollback(false)
    void shouldSaveClient() throws Exception {
        ResultActions resultActions = mockMvc.perform(post(API)
                .contentType(JSON_CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(clientDTO)))
                .andExpect(status().isCreated());
        MvcResult mvcResult = resultActions.andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        Integer integerId = JsonPath.parse(response).read("id");
        clientDTO.setId(integerId.longValue());
    }

    @Order(2)
    @Test
    void shouldFindByCpf() throws Exception {
        mockMvc.perform(get(API.concat("/by-cpf"))
                .contentType(JSON_CONTENT_TYPE)
                .param("cpf", clientDTO.getCpf()))
                .andExpect(status().isOk());
    }

    @Order(3)
    @Test
    void shouldUpdateClient() throws Exception {
        clientDTO.setNome("Diego Fagundes Arantes do Nascimento");
        mockMvc.perform(put(API)
                .contentType(JSON_CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(clientDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value(clientDTO.getNome()));
    }

    @Order(4)
    @Test
    void shouldNotSaveClient() throws Exception {
        clientDTO.setCpf("1234567");
        mockMvc.perform(post(API)
                .contentType(JSON_CONTENT_TYPE)
                .content(objectMapper.writeValueAsString(clientDTO)))
                .andExpect(status().isBadRequest());
    }

    @Order(5)
    @Test
    void shouldNotDeleteClient() throws Exception {
        mockMvc.perform(delete(API.concat("/999999999999999")))
                .andExpect(status().isNotFound());
    }

    @Order(6)
    @Test
    void shouldDeleteClient() throws Exception {
        mockMvc.perform(delete(API + "/" + clientDTO.getId()))
                .andExpect(status().isOk());
    }

}
