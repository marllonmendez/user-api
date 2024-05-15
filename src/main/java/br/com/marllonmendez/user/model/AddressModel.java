package br.com.marllonmendez.user.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.*;
import jakarta.persistence.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "tb_address")
public class AddressModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JsonAlias("cep")
    private String zipcode;
    @JsonAlias("logradouro")
    private String street;
    @JsonAlias("localidade")
    private String city;
    @JsonAlias("bairro")
    private String neighborhood;
    @JsonAlias("uf")
    private String state;

    private String number;
    private String typeAddress;
    private UUID idUser;
}
