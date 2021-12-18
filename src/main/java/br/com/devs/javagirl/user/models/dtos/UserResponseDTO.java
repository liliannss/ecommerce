package br.com.devs.javagirl.user.models.dtos;

import br.com.devs.javagirl.user.models.Address;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(example = "TESTE")
    @NotBlank(message = "Name required")
    @JsonProperty(value = "nome")
    private String name;

    @JsonProperty(value = "nascimento")
    private LocalDate birthdate;

    private String rg;

    @CPF
    @Schema(example = "29583329002")
    private String cpf;

    @JsonProperty(value = "telefone")
    private String phone;

    @JsonProperty(value = "celular")
    private String cellPhone;

    @Email
    @Schema(example = "test@test")
    private String email;

    @JsonProperty(value = "endereco")
    private Address address;
}