package br.com.devs.javagirl.user.services;

import br.com.devs.javagirl.user.models.Address;
import br.com.devs.javagirl.user.models.UserEntity;
import br.com.devs.javagirl.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final CepServiceImpl cepService;

    @Override
    public UserEntity createFeign(String cep, UserEntity userEntity) {
        Address address = getAddress(cep);
        userEntity.setAddress(address);

        UserEntity userEntitySaved = repository.save(userEntity);

        log.info("UserEntity saved={}", userEntitySaved);
        return userEntitySaved;
    }

    @Override
    public UserEntity createWebClient(String cep, UserEntity userEntity) {
        Address address = cepService.getCepWithWebClient(cep);
        userEntity.setAddress(address);

        UserEntity userEntitySaved = repository.save(userEntity);

        log.info("UserEntity saved={}", userEntitySaved);
        return userEntitySaved;
    }

    @Override
    public UserEntity createRestTemplate(String cep, UserEntity userEntity) {
        Address address = cepService.getCepWithRestTemplate(cep);
        userEntity.setAddress(address);

        UserEntity userEntitySaved = repository.save(userEntity);

        log.info("UserEntity saved={}", userEntitySaved);
        return userEntitySaved;
    }

    private Address getAddress(String cep) {
        Address address = cepService.getCepWithFeign(cep);

        log.info("Address found={}", address);
        return address;
    }

    @Override
    public UserEntity findById(Long id) {
        UserEntity userEntity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "user.not.found"));

        log.info("UserEntity found={}", userEntity);
        return userEntity;
    }

    @Override
    public List<UserEntity> findByNameAndEmailQueryMethods(String name, String email) {
        List<UserEntity> userEntity = repository.findByNameContainingOrEmailContainingAllIgnoreCase(name, email);

        log.info("UserEntity found={}", userEntity);
        return userEntity;
    }

    @Override
    public List<UserEntity> findByNameAndEmailJPQL(String name, String email) {
        List<UserEntity> userEntity = repository.findByNameContainingOrEmailContainingAllIgnoreCaseJPQL(name, email);

        log.info("UserEntity found={}", userEntity);
        return userEntity;
    }

    @Override
    public List<UserEntity> findByNameAndEmailNativeQuery(String name, String email) {
        List<UserEntity> userEntity = repository.findByNameContainingOrEmailContainingAllIgnoreCaseNativeQuery(name, email);

        log.info("UserEntity found={}", userEntity);
        return userEntity;
    }

}