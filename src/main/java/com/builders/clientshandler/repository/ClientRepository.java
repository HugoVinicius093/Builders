package com.builders.clientshandler.repository;

import com.builders.clientshandler.model.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByCpf(String cpf);

    Page<Client> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    Page<Client> findByCepContainingIgnoreCase(String cep, Pageable pageable);

    Optional<Client> findByEmail(String cpf);


}
