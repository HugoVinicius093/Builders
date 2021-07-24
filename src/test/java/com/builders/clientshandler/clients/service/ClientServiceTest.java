package com.builders.clientshandler.clients.service;

import com.builders.clientshandler.model.entities.Client;
import com.builders.clientshandler.repository.ClientRepository;
import com.builders.clientshandler.service.ClientService;
import com.builders.clientshandler.service.exception.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.Optional;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    private Client client;

    @Before
    public void createDummyClient() {
        log.info("--- INICIANDO TESTES DE SERVIÇO ---");
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
    public void shouldFindClientByCpf() throws ClientException {
        Mockito.when(clientRepository.findByCpf(client.getCpf())).thenReturn(Optional.ofNullable(client));

        Optional<Client> returnedClient = Optional.ofNullable(clientService.findClientByCpf(client.getCpf()));

        if (returnedClient.isPresent()) {
            Assertions.assertEquals(3L, returnedClient.get().getId());
            Assertions.assertEquals("diego_fagundes@gmail.com", returnedClient.get().getEmail());
            Assertions.assertEquals("54987635266", returnedClient.get().getCpf());
        }
    }

    @Test
    public void shouldFindClientByEmail() throws ClientException {
        Mockito.when(clientRepository.findByEmail(client.getEmail())).thenReturn(Optional.ofNullable(client));

        Optional<Client> returnedClient = Optional.ofNullable(clientService.findClientsByEmail(client.getEmail()));

        if (returnedClient.isPresent()) {
            Assertions.assertEquals(3L, returnedClient.get().getId());
            Assertions.assertEquals("diego_fagundes@gmail.com", returnedClient.get().getEmail());
            Assertions.assertEquals("54987635266", returnedClient.get().getCpf());
        }
    }

/*    @Test
    public void shouldCreateClient() {
        Mockito.when(clientRepository.save(Mockito.any(Client.class))).thenReturn(new Client());
        Mockito.when(clientRepository.save(client).thenReturn(client);

        ClientDTO clientoDto = clientService.createClient(client);
    }*/
}
