package br.com.ecommerce.user.controllers;

import br.com.ecommerce.user.models.Address;
import br.com.ecommerce.user.models.UserEntity;
import br.com.ecommerce.user.models.dtos.ErrorDTO;
import br.com.ecommerce.user.models.dtos.UserRequestDTO;
import br.com.ecommerce.user.models.dtos.UserRequestUpdateDTO;
import br.com.ecommerce.user.models.dtos.UserResponseDTO;
import br.com.ecommerce.user.services.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Sort.Direction.valueOf;
import static org.springframework.data.domain.Sort.by;
import static org.springframework.http.HttpStatus.*;

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
    @Tag(name = "CREATE")
    @Operation(summary = "Create User", description = "Create User using Feign")
    @ApiResponses(value = {
            @ApiResponse(description = "User Created with Success", responseCode = "201",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    public UserResponseDTO createWithFeign(@PathVariable @Schema(example = "17800970") String cep,
                                           @Valid @RequestBody UserRequestDTO userRequestDTO) {
        log.info("Start method createWithFeign={}", userRequestDTO);
        UserEntity userEntity = converterDTOToEntity(userRequestDTO);

        buildAddress(userRequestDTO, userEntity);

        UserEntity userCreated = service.createWithFeign(cep, userEntity);

        UserResponseDTO userDtoResponse = converterEntityToDTO(userCreated);
        log.info("Finish method createWithFeign={}", userDtoResponse);

        return userDtoResponse;
    }

    @PostMapping("/{cep}/web-client")
    @Tag(name = "CREATE")
    @Operation(summary = "Create User", description = "Create User using WebClient")
    @ApiResponses(value = {
            @ApiResponse(description = "User Created with Success", responseCode = "201",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    public ResponseEntity<UserResponseDTO> createWithWebClient(@PathVariable @Schema(example = "17800970") String cep,
                                                               @Valid @RequestBody UserRequestDTO userRequestDTO) {
        log.info("Start method createWithWebClient={}", userRequestDTO);
        UserEntity userEntity = converterDTOToEntity(userRequestDTO);

        buildAddress(userRequestDTO, userEntity);

        UserEntity userCreated = service.createWithWebClient(cep, userEntity);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();

        UserResponseDTO userDtoResponse = converterEntityToDTO(userCreated);
        log.info("Finish method createWithWebClient={}", userDtoResponse);

        return ResponseEntity.created(uri).body(userDtoResponse);
    }

    @PostMapping("/{cep}/rest-template")
    @ResponseStatus(CREATED)
    @Tag(name = "CREATE")
    @Operation(summary = "Create User", description = "Create User using RestTemplate")
    @ApiResponses(value = {
            @ApiResponse(description = "User Created with Success", responseCode = "201",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    public UserResponseDTO createWithRestTemplate(@PathVariable @Schema(example = "17800970") String cep,
                                                  @Valid @RequestBody UserRequestDTO userRequestDTO) {
        log.info("Start method createWithRestTemplate={}", userRequestDTO);
        UserEntity userEntity = converterDTOToEntity(userRequestDTO);

        buildAddress(userRequestDTO, userEntity);

        UserEntity userCreated = service.createWithRestTemplate(cep, userEntity);

        UserResponseDTO userDtoResponse = converterEntityToDTO(userCreated);
        log.info("Finish method createWithRestTemplate={}", userDtoResponse);

        return userDtoResponse;
    }

    private void buildAddress(UserRequestDTO userRequestDTO, UserEntity userEntity) {
        Address buildAddress = Address.builder()
                .number(userRequestDTO.getNumber())
                .build();

        userEntity.setAddress(buildAddress);
    }

    @Hidden
    @GetMapping("/{id}")
    @ResponseStatus(OK)
    @Tag(name = "READ")
    @Operation(summary = "Find User by Id", description = "Find User using CrudRepository")
    @ApiResponses(value = {
            @ApiResponse(description = "User Consulted with Success", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(description = "User Not Found", responseCode = "404",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    public UserResponseDTO findById(@PathVariable Long id) {
        log.info("Start method findById={}", id);
        UserEntity userConsulted = service.findById(id);

        UserResponseDTO userDTO = converterEntityToDTO(userConsulted);
        log.info("Finish method findById={}", userDTO);

        return userDTO;
    }

    @GetMapping("query-method")
    @ResponseStatus(OK)
    @Tag(name = "READ")
    @Operation(summary = "Find Users by Name and Email", description = "Find User using Query Method")
    @ApiResponses(value = {
            @ApiResponse(description = "Users Consulted with Success", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class)))
    })
    public List<UserResponseDTO> findByNameAndEmailWithQueryMethod(@RequestParam @Schema(example = "Teste") String name,
                                                                   @RequestParam @Schema(example = "teste") String email) {
        log.info("Start method findByNameAndEmailWithQueryMethod name={} email={}", name, email);
        List<UserEntity> userEntityList = service.findByNameAndEmailWithQueryMethod(name, email);

        List<UserResponseDTO> userResponseDTOList = converterEntityListToDTOList(userEntityList);
        log.info("Finish method findByNameAndEmailWithQueryMethod={}", userResponseDTOList);

        return userResponseDTOList;
    }

    @GetMapping("/jpql")
    @ResponseStatus(OK)
    @Tag(name = "READ")
    @Operation(summary = "Find Users by Name and Email", description = "Find User using JPQL")
    @ApiResponses(value = {
            @ApiResponse(description = "Users consulted with Success", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class)))
    })
    public List<UserResponseDTO> findByNameAndEmailWithJPQL(@RequestParam @Schema(example = "Teste") String name,
                                                            @RequestParam @Schema(example = "teste") String email) {
        log.info("Start method findByNameAndEmailWithJPQL name={} email={}", name, email);
        List<UserEntity> userEntityList = service.findByNameAndEmailWithJPQL(name, email);

        List<UserResponseDTO> userResponseDTOList = converterEntityListToDTOList(userEntityList);
        log.info("Finish method findByNameAndEmailWithJPQL={}", userResponseDTOList);

        return userResponseDTOList;
    }

    @GetMapping("/native-query")
    @ResponseStatus(OK)
    @Tag(name = "READ")
    @Operation(summary = "Find Users by Name and Email", description = "Find User using Native Query")
    @ApiResponses(value = {
            @ApiResponse(description = "Users consulted with Success", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class)))
    })
    public List<UserResponseDTO> findByNameAndEmailWithNativeQuery(@RequestParam @Schema(example = "Teste") String name,
                                                                   @RequestParam @Schema(example = "teste") String email) {
        log.info("Start method findByNameAndEmailWithNativeQuery name={} email={}", name, email);
        List<UserEntity> userEntityList = service.findByNameAndEmailWithNativeQuery(name, email);

        List<UserResponseDTO> userResponseDTOList = converterEntityListToDTOList(userEntityList);
        log.info("Finish method findByNameAndEmailWithNativeQuery={}", userResponseDTOList);

        return userResponseDTOList;
    }

    @GetMapping("/paginated")
    @ResponseStatus(OK)
    @Tag(name = "READ")
    @Operation(summary = "Find All", description = "Find User using Pageable")
    @ApiResponses(value = {
            @ApiResponse(description = "Users consulted with Success", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class)))
    })
    public Page<UserResponseDTO> findByNameAndEmailPaginated(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "15") Integer size,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,
            @RequestParam(value = "sort", defaultValue = "name") String sort) {
        log.info("Start method findByNameAndEmailPaginated page={} size={}", page, size);
        Page<UserEntity> userEntityPage = service.findByNameAndEmailPaginated(of(page, size, by(valueOf(direction), sort)));

        List<UserResponseDTO> userResponseDTOList = converterEntityListToDTOList(userEntityPage.toList());
        log.info("Finish method findByNameAndEmailPaginated={}", userResponseDTOList);

        return new PageImpl<>(userResponseDTOList);
    }

    private List<UserResponseDTO> converterEntityListToDTOList(List<UserEntity> userEntityList) {
        log.info("Start converterEntityToDTO={}", userEntityList);
        return userEntityList
                .stream()
                .map(this::converterEntityToDTO)
                .collect(toList());
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    @Tag(name = "UPDATE")
    @Operation(summary = "Update User by Id", description = "Update User")
    @ApiResponses(value = {
            @ApiResponse(description = "User Updated with Success", responseCode = "200",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(description = "User Not Found", responseCode = "404",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    public UserResponseDTO update(@PathVariable Long id,
                                  @RequestBody UserRequestUpdateDTO userRequestUpdateDTO) {
        log.info("Start method update={}", userRequestUpdateDTO);
        UserEntity userEntity = converterDTOToEntity(userRequestUpdateDTO);

        UserEntity userResponseDTO = service.update(id, userEntity);

        UserResponseDTO userResponseDTOList = converterEntityToDTO(userResponseDTO);

        log.info("Finish method update={}", userResponseDTOList);
        return userResponseDTOList;
    }

    private UserEntity converterDTOToEntity(UserRequestDTO userRequestDTO) {
        return mapper.map(userRequestDTO, UserEntity.class);
    }

    private UserEntity converterDTOToEntity(UserRequestUpdateDTO userRequestUpdateDTO) {
        return mapper.map(userRequestUpdateDTO, UserEntity.class);
    }

    private UserResponseDTO converterEntityToDTO(UserEntity userCreated) {
        return mapper.map(userCreated, UserResponseDTO.class);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @Tag(name = "DELETE")
    @Operation(summary = "Delete User by Id", description = "Delete User")
    @ApiResponses(value = {
            @ApiResponse(description = "User Deleted with Success", responseCode = "204"),
            @ApiResponse(description = "User Not Found", responseCode = "404",
                    content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
    })
    public void delete(@PathVariable Long id) {
        log.info("Start method delete={}", id);

        service.delete(id);

        log.info("Finish method delete");
    }
}