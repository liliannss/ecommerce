package br.com.ecommerce.user.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@ToString
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