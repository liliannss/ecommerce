package br.com.devs.javagirl.user.controllers;

import br.com.devs.javagirl.user.models.UserEntity;
import br.com.devs.javagirl.user.models.dtos.UserDTO;
import br.com.devs.javagirl.user.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@Log4j2
@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private ModelMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    @Operation(summary = "Create a New User")
    @ApiResponses(value = {
            @ApiResponse(description = "User Created with Success", responseCode = "201",
                    content = @Content(schema = @Schema(implementation = UserDTO.class)))
    })
    public UserDTO create(@RequestBody UserDTO userDTO) {
        log.info("Start method create={}", userDTO);
        UserEntity userEntity = mapper.map(userDTO, UserEntity.class);
        UserEntity userCreated = service.create(userEntity);

        return mapper.map(userCreated, UserDTO.class);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find User by Id")
    @ApiResponses(value = {
            @ApiResponse(description = "User consulted with Success", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = UserDTO.class)))
    })
    public UserDTO findById(@PathVariable Long id) {
        UserEntity userConsulted = service.findById(id);

        return mapper.map(userConsulted, UserDTO.class);
    }
}