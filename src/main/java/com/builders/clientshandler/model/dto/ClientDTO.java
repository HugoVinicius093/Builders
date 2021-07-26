package com.builders.clientshandler.model.dto;

import com.builders.clientshandler.model.entities.Client;
import com.builders.clientshandler.service.validation.constraints.Cep;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@Builder
@Data
public class ClientDTO extends RepresentationModel<ClientDTO> implements Serializable {

    private Long id;

    @NotBlank(message = "Field not filled in!")
    @Pattern(regexp = "^[[ ]|\\p{L}*]+$", message = "Name must contain only letters!")
    private String nome;

    @CPF(message = "Invalid Cpf!")
    private String cpf;

    @NotBlank(message = "Field not filled in!")
    private String endereco;

    @NotBlank(message = "Field not filled in!")
    private String cidade;

    @NotBlank(message = "Field not filled in!")
    private String estado;

    @NotBlank(message = "Field not filled in!")
    @Cep(message = "Invalid Cep!")
    private String cep;

    @NotBlank(message = "Field not filled in!")
    @Email(message = "Invalid E-mail!")
    private String email;

    private Date dataNascimento;

    public Client toClient() {
        return Client.builder()
                .id(this.id)
                .nome(this.nome)
                .cpf(this.cpf)
                .endereco(this.endereco)
                .cidade(this.cidade)
                .estado(this.estado)
                .cep(this.cep)
                .email(this.email)
                .dataNascimento(this.dataNascimento).build();
    }

}
