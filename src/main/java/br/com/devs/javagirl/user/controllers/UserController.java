package br.com.devs.javagirl.user.controllers;

import br.com.devs.javagirl.user.models.UserEntity;
import br.com.devs.javagirl.user.models.dtos.ErrorDTO;
import br.com.devs.javagirl.user.models.dtos.UserRequestDTO;
import br.com.devs.javagirl.user.models.dtos.UserResponseDTO;
import br.com.devs.javagirl.user.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;

import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Sort.Direction.valueOf;
import static org.springframework.data.domain.Sort.by;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Log4j2
@Validated
@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private ModelMapper mapper;

    @PostMapping("/{cep}/feign")
    @ResponseStatus(CREATED)
    @Operation(summary = "Create a New User")
    @ApiResponses(value = {
            @ApiResponse(description = "User Created with Success", responseCode = "201",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    public UserResponseDTO createFeign(@PathVariable String cep, @Valid @RequestBody UserRequestDTO userRequestDTO) {
        log.info("Start method createFeign={}", userRequestDTO);
        UserEntity userEntity = mapper.map(userRequestDTO, UserEntity.class);
        UserEntity userCreated = service.createFeign(cep, userEntity);

        UserResponseDTO userDtoResponse = mapper.map(userCreated, UserResponseDTO.class);
        log.info("Finish method createFeign={}", userDtoResponse);

        return userDtoResponse;
    }

    //@Hidden
    @PostMapping("/{cep}/web-client")
    @ResponseStatus(CREATED)
    @Operation(summary = "Create a New User")
    @ApiResponses(value = {
            @ApiResponse(description = "User Created with Success", responseCode = "201",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    public UserResponseDTO createWebClient(@PathVariable String cep, @Valid @RequestBody UserRequestDTO userRequestDTO) {
        log.info("Start method createWebClient={}", userRequestDTO);
        UserEntity userEntity = mapper.map(userRequestDTO, UserEntity.class);
        UserEntity userCreated = service.createFeign(cep, userEntity);

        UserResponseDTO userDtoResponse = mapper.map(userCreated, UserResponseDTO.class);
        log.info("Finish method createWebClient={}", userDtoResponse);

        return userDtoResponse;
    }

    //@Hidden
    @PostMapping("/{cep}/rest-template")
    @ResponseStatus(CREATED)
    @Operation(summary = "Create a New User")
    @ApiResponses(value = {
            @ApiResponse(description = "User Created with Success", responseCode = "201",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    public UserResponseDTO createRestTemplate(@PathVariable String cep, @Valid @RequestBody UserRequestDTO userRequestDTO) {
        log.info("Start method createRestTemplate={}", userRequestDTO);
        UserEntity userEntity = mapper.map(userRequestDTO, UserEntity.class);
        UserEntity userCreated = service.createRestTemplate(cep, userEntity);

        UserResponseDTO userDtoResponse = mapper.map(userCreated, UserResponseDTO.class);
        log.info("Finish method createRestTemplate={}", userDtoResponse);

        return userDtoResponse;
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    @Operation(summary = "Find User by Id")
    @ApiResponses(value = {
            @ApiResponse(description = "User consulted with Success", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(description = "User Not Found", responseCode = "404",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    public UserResponseDTO findById(@PathVariable Long id) {
        log.info("Start method findById={}", id);
        UserEntity userConsulted = service.findById(id);

        UserResponseDTO userDTO = mapper.map(userConsulted, UserResponseDTO.class);
        log.info("Finish method findById={}", userDTO);

        return userDTO;
    }

    @GetMapping("query-methods")
    @ResponseStatus(OK)
    @Operation(summary = "Find User by Name and Email - Query Methods")
    @ApiResponses(value = {
            @ApiResponse(description = "User consulted with Success", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class)))
    })
    public List<UserResponseDTO> findByNameAndEmailQueryMethods(@RequestParam String name, String email) {
        log.info("Start method findByNameAndEmailQueryMethods name={} email={}", name, email);
        List<UserEntity> userEntityList = service.findByNameAndEmailQueryMethods(name, email);

        Type typeList = new TypeToken<List<UserResponseDTO>>() {
        }.getType();

        //TODO adicionar log
        return mapper.map(userEntityList, typeList);
    }

    //@Hidden
    @GetMapping("/jpql")
    @ResponseStatus(OK)
    @Operation(summary = "Find User by Name and Email - JPQL")
    @ApiResponses(value = {
            @ApiResponse(description = "User consulted with Success", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class)))
    })
    public List<UserResponseDTO> findByNameAndEmailJPQL(@RequestParam String name, @RequestParam String email) {
        log.info("Start method findByNameAndEmailJPQL name={} email={}", name, email);
        List<UserEntity> userEntityList = service.findByNameAndEmailJPQL(name, email);

        Type typeList = new TypeToken<List<UserResponseDTO>>() {
        }.getType();

        //TODO adicionar log
        return mapper.map(userEntityList, typeList);
    }

    //@Hidden
    @GetMapping("/native-query")
    @ResponseStatus(OK)
    @Operation(summary = "Find User by Name and Email - Native Query")
    @ApiResponses(value = {
            @ApiResponse(description = "User consulted with Success", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class)))
    })
    public List<UserResponseDTO> findByNameAndEmailNativeQuery(@RequestParam String name, @RequestParam String email) {
        log.info("Start method findByNameAndEmailNativeQuery name={} email={}", name, email);
        List<UserEntity> userEntityList = service.findByNameAndEmailNativeQuery(name, email);

        Type typeList = new TypeToken<List<UserResponseDTO>>() {
        }.getType();

        //TODO adicionar log
        return mapper.map(userEntityList, typeList);
    }

    @GetMapping("paginated")
    @ResponseStatus(OK)
    @Operation(summary = "Find User by Name and Email - Paginated")
    @ApiResponses(value = {
            @ApiResponse(description = "User consulted with Success", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class)))
    })
    public Page<UserResponseDTO> findByNameAndEmailPaginated(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "sort", defaultValue = "id") String sort) {

        Page<UserEntity> userEntityList = service.findByNameAndEmailPaginated(of(page, size, by(valueOf(direction), sort)));

        Type typeList = new TypeToken<Page<UserResponseDTO>>() {}.getType();

        return mapper.map(userEntityList, typeList);
    }

}