package com.builders.clientshandler.controller;

import com.builders.clientshandler.model.dto.ClientDTO;
import com.builders.clientshandler.model.entities.Client;
import com.builders.clientshandler.service.ClientService;
import com.builders.clientshandler.service.exception.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RestController
@RequestMapping("/v1/api")
public class ClientController {

    private static final String DELETE = "Delete Client";

    private static final String UPDATE = "Update Client";


    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/clients")
    public ResponseEntity<Page<ClientDTO>> getAllClients(@PageableDefault(size = 5,
            sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("Fetching clients.");
        Page<Client> clients = clientService.getAllClients(pageable);

        if (clients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clients.map(client -> {
            ClientDTO clientDto = client.toDTO();
            clientDto.add(linkTo(methodOn(ClientController.class).deleteClientById(clientDto.getId())).withRel((DELETE)));
            clientDto.add(linkTo(methodOn(ClientController.class).updateClient(clientDto)).withRel((UPDATE)));
            return clientDto;
        }), HttpStatus.OK);
    }

    @PostMapping(value = "/clients")
    public ResponseEntity<ClientDTO> createClient(@RequestBody @Valid ClientDTO clientDto) {
        log.info("Creating client.");
        ClientDTO clientDtoResult = clientService.createClient(clientDto.toClient());
        clientDtoResult.add(linkTo(methodOn(ClientController.class).deleteClientById(clientDtoResult.getId())).withRel((DELETE)));
        clientDto.add(linkTo(methodOn(ClientController.class).updateClient(clientDto)).withRel((UPDATE)));
        return new ResponseEntity<>(clientDtoResult, HttpStatus.CREATED);
    }

    @PutMapping(value = "/clients")
    public ResponseEntity<ClientDTO> updateClient(@RequestBody @Valid ClientDTO clientDto) {
        log.info("Updating client.");
        try {
            return new ResponseEntity<>(clientService.updateClient(clientDto.toClient()), HttpStatus.OK);
        } catch (ClientException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping(value = "/clients/{id}")
    public ResponseEntity<Object> deleteClientById(@PathVariable("id") long id) {
        log.info("Deleting client.");
        try {
            clientService.deleteClient(id);
        } catch (ClientException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/clients")
    public ResponseEntity<Object> deleteAllClients() {
        log.info("Deleting every single Client.");
        try {
            clientService.deleteAllClients();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/clients/by-cpf")
    public ResponseEntity<ClientDTO> findClientByCpf(@RequestParam(name = "cpf") String cpf) {
        log.info("Fetching client by cpf");
        try {
            ClientDTO clientDtoResult = clientService.findClientByCpf(cpf).toDTO();
            clientDtoResult.add(linkTo(methodOn(ClientController.class).deleteClientById(clientDtoResult.getId())).withRel((DELETE)));
            clientDtoResult.add(linkTo(methodOn(ClientController.class).updateClient(clientDtoResult)).withRel((UPDATE)));
            return new ResponseEntity<>(clientDtoResult, HttpStatus.OK);
        } catch (ClientException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping(value = "/clients/by-nome")
    public ResponseEntity<Page<ClientDTO>> findClientsByNome(@RequestParam(name = "nome") String nome, @PageableDefault(size = 5,
            sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("Fetching client by nome");

        Page<Client> clients = clientService.findClientsByNome(nome, pageable);
        if (clients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clients.map(client -> {
            ClientDTO clientDto = client.toDTO();
            clientDto.add(linkTo(methodOn(ClientController.class).deleteClientById(clientDto.getId())).withRel((DELETE)));
            clientDto.add(linkTo(methodOn(ClientController.class).updateClient(clientDto)).withRel((UPDATE)));
            return clientDto;
        }), HttpStatus.OK);
    }

    @GetMapping(value = "/clients/by-cep")
    public ResponseEntity<Page<ClientDTO>> findClientsByCep(@RequestParam(name = "cep") String cep, @PageableDefault(size = 5,
            sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("Fetching client by cep");

        Page<Client> clients = clientService.findClientsByCep(cep, pageable);
        if (clients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(clients.map(client -> {
            ClientDTO clientDto = client.toDTO();
            clientDto.add(linkTo(methodOn(ClientController.class).deleteClientById(clientDto.getId())).withRel((DELETE)));
            clientDto.add(linkTo(methodOn(ClientController.class).updateClient(clientDto)).withRel((UPDATE)));
            return clientDto;
        }), HttpStatus.OK);
    }

    @GetMapping(value = "/clients/by-email")
    public ResponseEntity<ClientDTO> findClientsByEmail(@RequestParam(name = "email") String email) {
        log.info("Fetching client by email");
        try {
            ClientDTO clientDtoResult = clientService.findClientsByEmail(email).toDTO();
            clientDtoResult.add(linkTo(methodOn(ClientController.class).deleteClientById(clientDtoResult.getId())).withRel((DELETE)));
            clientDtoResult.add(linkTo(methodOn(ClientController.class).updateClient(clientDtoResult)).withRel((UPDATE)));
            return new ResponseEntity<>(clientDtoResult, HttpStatus.OK);
        } catch (ClientException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


}
