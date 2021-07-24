package com.builders.clientshandler.model.entities;

import com.builders.clientshandler.model.dto.ClientDTO;
import com.builders.clientshandler.model.interfaces.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "clients", uniqueConstraints = {
        @UniqueConstraint(columnNames = "cpf"),
        @UniqueConstraint(columnNames = "email")})
public class Client implements BaseEntity {

    private static final long serialVersionUID = 6228462290387277865L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "cpf", length = 14, nullable = false)
    private String cpf;

    @Column(name = "endereco", length = 100, nullable = false)
    private String endereco;

    @Column(name = "cidade", length = 50, nullable = false)
    private String cidade;

    @Column(name = "estado", length = 25, nullable = false)
    private String estado;

    @Column(name = "cep", length = 15, nullable = false)
    private String cep;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento")
    private Date dataNascimento;

    public ClientDTO toDTO() {
        return ClientDTO.builder()
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
