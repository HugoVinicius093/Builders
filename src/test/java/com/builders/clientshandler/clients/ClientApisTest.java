/*
package com.builders.clientshandler.clients;

import com.builders.clientshandler.controller.ClientController;
import com.builders.clientshandler.model.entities.Client;
import com.builders.clientshandler.repository.ClientRepository;
import com.builders.clientshandler.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientController.class)
public class ClientApisTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    ClientService clientService;

    @MockBean
    ClientRepository clientRepository;

    Client client1 = Client.builder()
            .id(1L)
            .cep("19020-160")
            .cpf("42327219833")
            .cidade("PP")
            .dataNascimento(new Date())
            .email("hugo@gmail.com")
            .endereco("fruas")
            .nome("Hugo Moraes").build();
    Client client2 = Client.builder()
            .id(2L)
            .cep("19020-160")
            .cpf("56498713542")
            .cidade("PP")
            .dataNascimento(new Date())
            .email("hugo@gmail.com")
            .endereco("fruas")
            .nome("Hugo Moraes").build();
    Client client3 = Client.builder()
            .id(3L)
            .cep("19020-160")
            .cpf("42356498733")
            .cidade("PP")
            .dataNascimento(new Date())
            .email("hugo@gmail.com")
            .endereco("fruas")
            .nome("Hugo Moraes").build();

    private List<Client> clientList = new ArrayList<>(Arrays.asList(client1,client2,client3));
*/
/*    @Test
    public void shouldReturnAllClients() throws Exception {
        List<Client> records = new ArrayList<>(Arrays.asList(client1, client2, client3));
        Page<Client> pageClient = new PageImpl<>(records);
        Mockito.when(clientService.getAllClients(null)).thenReturn(pageClient);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/clients")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].name", is("Hugo Moraes")));
    }*//*


    @Test
    public void createRecord_success() throws Exception {
        Client client1 = Client.builder()
                .id(1L)
                .cep("19020-160")
                .cpf("42327219833")
                .cidade("PP")
                .dataNascimento(new Date())
                .email("hugo@gmail.com")
                .endereco("fruas")
                .nome("Hugo Moraes").build();

        Mockito.when(clientService.createClient(client1)).thenReturn(client1.toDTO());

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/client")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(client1));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
   */
/*             .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("John Doe")))*//*
;
    }

    @Test
    public void getPatientById_success() throws Exception {
        Mockito.when(clientService.findClientByCpf(client1.getCpf())).thenReturn(client1);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/clients/by-cpf")
                .param(client1.getCpf())
                */
/*.contentType(MediaType.APPLICATION_JSON)*//*
)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.nome", is("Hugo Moraes")));
    }

*/
/*    @Test
    public void shouldDeleteClientById() throws Exception {
        Mockito.when(clientService.deleteClient(client2.getId())).then(clientList.remove(2L));

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/clients/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }*//*

}
*/
