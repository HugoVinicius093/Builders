package com.builders.clientshandler.service;

import com.builders.clientshandler.model.dto.ClientDTO;
import com.builders.clientshandler.model.entities.Client;
import com.builders.clientshandler.repository.ClientRepository;
import com.builders.clientshandler.service.exception.ClientException;
import com.builders.clientshandler.service.inputtreatment.TreatmentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private final TreatmentFactory treatmentFactory;

    @Autowired
    public ClientService(ClientRepository clientRepository, TreatmentFactory treatmentFactory) {
        this.clientRepository = clientRepository;
        this.treatmentFactory = treatmentFactory;
    }

    /**
     * @param pageable
     * @return
     */
    public Page<Client> getAllClients(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    /**
     * @param client
     * @return
     */
    @Transactional
    public ClientDTO createClient(Client client) {
        treatmentFactory.execute(client);
        return clientRepository.save(client).toDTO();
    }

    /**
     * @param client
     * @return
     * @throws ClientException
     */
    @Transactional
    public ClientDTO updateClient(Client client) throws ClientException {
        if (!clientRepository.findById(client.getId()).isPresent()) {
            throw new ClientException(String.format("Client id %s not found!", client.getId()));
        }
        return clientRepository.save(client).toDTO();
    }

    /**
     * @param id
     * @throws ClientException
     */
    @Transactional
    public void deleteClient(Long id) throws ClientException {
        Optional<Client> client = clientRepository.findById(id);
        if (!client.isPresent()) {
            throw new ClientException(String.format("Client id %s not found!", id));
        }
        deleteClient(client.get());
    }

    /**
     * @param client
     */
    @Transactional
    public void deleteClient(Client client) {
        clientRepository.delete(client);
    }

    @Transactional
    public void deleteAllClients() {
        clientRepository.deleteAll();
    }

    /**
     * @param cpf
     * @return
     * @throws ClientException
     */
    @Transactional
    public Client findClientByCpf(String cpf) throws ClientException {
        return clientRepository.findByCpf(cpf).orElseThrow(() -> new ClientException("CPF not found!"));
    }

    /**
     * @param nome
     * @param pageable
     * @return
     */
    @Transactional
    public Page<Client> findClientsByNome(String nome, Pageable pageable) {
        return clientRepository.findByNomeContainingIgnoreCase(nome, pageable);
    }

    /**
     * @param nome
     * @param pageable
     * @return
     */
    @Transactional
    public Page<Client> findClientsByCep(String nome, Pageable pageable) {
        return clientRepository.findByCepContainingIgnoreCase(nome, pageable);
    }


    /**
     * @param email
     * @return
     */
    @Transactional
    public Client findClientsByEmail(String email) throws ClientException {
        return clientRepository.findByEmail(email).orElseThrow(() -> new ClientException("E-mail not found!"));
    }
}
