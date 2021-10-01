package br.com.devs.javagirl.user.controllers;

import br.com.devs.javagirl.user.models.UserEntity;
import br.com.devs.javagirl.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    @ResponseStatus(CREATED)
    public UserEntity create(@RequestBody UserEntity userEntity) {
        return service.create(userEntity);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserEntity findById(@PathVariable Long id) {
        return service.findById(id);
    }

}