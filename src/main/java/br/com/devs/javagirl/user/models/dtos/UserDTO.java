package br.com.devs.javagirl.user.models.dtos;

import br.com.devs.javagirl.user.models.Address;
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
public class UserDTO {

    @Schema(example = "BRUNA")
    @NotBlank(message = "Name required")
    private String name;

    private LocalDate birthdate;

    private String rg;

    @CPF
    @Schema(example = "29583329002")
    private String cpf;

    private String phone;

    private String cellPhone;

    @Email
    @Schema(example = "test@test")
    private String email;
}