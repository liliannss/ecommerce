package br.com.devs.javagirl.user.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Builder
@Entity
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String nome;

    private LocalDate dataNascimento;

    private String rg;

    private String cpf;

    private String telefoneFixo;

    private String telefoneCelular;

    private String email;

    private String endereco;

}