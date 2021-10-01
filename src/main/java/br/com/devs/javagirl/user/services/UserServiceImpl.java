package br.com.devs.javagirl.user.services;

import br.com.devs.javagirl.user.models.UserEntity;
import br.com.devs.javagirl.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserEntity create(UserEntity userEntity) {
        return repository.save(userEntity);
    }

    @Override
    public UserEntity findById(Long id) {
        return repository.findById(id)
                .get();
    }

}