package br.com.devs.javagirl.user.models.dtos;

import br.com.devs.javagirl.user.models.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @Schema(example = "FULANO")
    private String name;

    private LocalDate birthdate;

    private String rg;

    private String cpf;

    private String phone;

    private String cellPhone;

    private String email;

   private Address address;
}
