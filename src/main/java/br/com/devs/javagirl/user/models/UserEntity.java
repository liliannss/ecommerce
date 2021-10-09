package br.com.devs.javagirl.user.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private LocalDate birthdate;

    private String rg;

    private String cpf;

    private String phone;

    private String cellPhone;

    private String email;

    private Address address;

}