package br.com.devs.javagirl.user.services;

import br.com.devs.javagirl.user.models.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity create(UserEntity userEntity);
    UserEntity findById(Long id);
    List<UserEntity> findByNameOrEmail(String name, String email);
}