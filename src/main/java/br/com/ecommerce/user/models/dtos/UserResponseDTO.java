package br.com.ecommerce.user.models.dtos;

import br.com.ecommerce.user.models.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private Long id;

    @NotBlank(message = "Name required")
    @JsonProperty(value = "nome")
    private String name;

    @JsonProperty(value = "nascimento")
    private LocalDate birthdate;

    private String rg;

    @CPF
    private String cpf;

    @JsonProperty(value = "telefone")
    private String phone;

    @JsonProperty(value = "celular")
    private String cellPhone;

    @Email
    private String email;

    @JsonProperty(value = "endereco")
    private Address address;
}