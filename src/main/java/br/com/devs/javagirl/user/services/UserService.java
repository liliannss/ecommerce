package br.com.devs.javagirl.user.services;

import br.com.devs.javagirl.user.models.UserEntity;

public interface UserService {

    UserEntity create(UserEntity userEntity);
    UserEntity findById(Long id);

}