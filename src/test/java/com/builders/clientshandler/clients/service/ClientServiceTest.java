package com.builders.clientshandler.clients.service;

import com.builders.clientshandler.model.entities.Client;
import com.builders.clientshandler.repository.ClientRepository;
import com.builders.clientshandler.service.ClientService;
import com.builders.clientshandler.service.exception.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

@Slf4j
@SpringBootTest
public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    private Client client;

    @BeforeAll
    public static void setup() {
        log.info("--- STARTING TESTS ON SERVICE ---");
    }

    @BeforeEach
    public void createDummyClient() {
        client = Client.builder()
                .id(3L)
                .cep("19020-160")
                .cpf("54987635266")
                .cidade("Osasco")
                .dataNascimento(new Date())
                .email("diego_fagundes@gmail.com")
                .endereco("Rua Euclides Segundo")
                .nome("Diego Fagundes").build();
    }


    @Test
    void shouldFindClientByCpf() throws ClientException {
        Mockito.when(clientRepository.findByCpf(client.getCpf())).thenReturn(Optional.ofNullable(client));

        Optional<Client> returnedClient = Optional.ofNullable(clientService.findClientByCpf(client.getCpf()));

        if (returnedClient.isPresent()) {
            Assertions.assertEquals(3L, returnedClient.get().getId());
            Assertions.assertEquals("diego_fagundes@gmail.com", returnedClient.get().getEmail());
            Assertions.assertEquals("54987635266", returnedClient.get().getCpf());
        }
    }

    @Test
    void shouldFindClientByEmail() throws ClientException {
        Mockito.when(clientRepository.findByEmail(client.getEmail())).thenReturn(Optional.ofNullable(client));

        Optional<Client> returnedClient = Optional.ofNullable(clientService.findClientsByEmail(client.getEmail()));

        if (returnedClient.isPresent()) {
            Assertions.assertEquals(3L, returnedClient.get().getId());
            Assertions.assertEquals("diego_fagundes@gmail.com", returnedClient.get().getEmail());
            Assertions.assertEquals("54987635266", returnedClient.get().getCpf());
        }
    }

}
