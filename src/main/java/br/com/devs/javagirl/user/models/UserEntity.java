package br.com.devs.javagirl.user.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "usuario_id")
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "nascimento")
    private LocalDate birthdate;

    private String rg;

    private String cpf;

    @Column(name = "telefone")
    private String phone;

    @Column(name = "celular")
    private String cellPhone;

    private String email;

    @Column(name = "endereco")
    private Address address;

}