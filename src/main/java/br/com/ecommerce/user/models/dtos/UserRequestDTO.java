package br.com.ecommerce.user.models.dtos;

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

    @Schema(example = "Teste")
    @NotBlank(message = "Name required")
    @JsonProperty(value = "nome")
    private String name;

    @Schema(example = "2021-12-18")
    @JsonProperty(value = "nascimento")
    private LocalDate birthdate;

    @Schema(example = "237253847")
    private String rg;

    @CPF
    @Schema(example = "45028438064")
    private String cpf;

    @Schema(example = "324-763-1348")
    @JsonProperty(value = "telefone")
    private String phone;

    @Schema(example = "251-269-0832")
    @JsonProperty(value = "celular")
    private String cellPhone;

    @Email
    @Schema(example = "teste@teste")
    private String email;

    @Schema(example = "111")
    @JsonProperty(value = "numero")
    private String number;
}