package br.com.devs.javagirl.user.models.dtos;

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
public class UserRequestDTO {

    @Schema(example = "TESTE")
    @NotBlank(message = "Name required")
    @JsonProperty(value = "nome")
    private String name;

    @Schema(example = "2021-12-18")
    @JsonProperty(value = "nascimento")
    private LocalDate birthdate;

    @Schema(example = "1111111111")
    private String rg;

    @CPF
    @Schema(example = "29583329002")
    private String cpf;

    @Schema(example = "1111111111")
    @JsonProperty(value = "telefone")
    private String phone;

    @Schema(example = "1111111111")
    @JsonProperty(value = "celular")
    private String cellPhone;

    @Email
    @Schema(example = "test@test")
    private String email;

    @JsonProperty(value = "numero")
    private String number;
}