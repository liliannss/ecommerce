package br.com.devs.javagirl.user.services.impl;

import br.com.devs.javagirl.user.models.Address;
import br.com.devs.javagirl.user.models.UserEntity;
import br.com.devs.javagirl.user.repositories.UserRepository;
import br.com.devs.javagirl.user.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        String number = getNumber(userEntity);

        Address address = getAddress(cep);
        buildEntity(userEntity, number, address);

        UserEntity userEntitySaved = saveUser(userEntity);

        log.info("UserEntity saved={}", userEntitySaved);
        return userEntitySaved;
    }

    private Address getAddress(String cep) {
        return cepService.getCepWithFeign(cep);
    }

    @Override
    public UserEntity createWebClient(String cep, UserEntity userEntity) {
        String number = getNumber(userEntity);

        Address address = cepService.getCepWithWebClient(cep);
        buildEntity(userEntity, number, address);

        UserEntity userEntitySaved = saveUser(userEntity);

        log.info("UserEntity saved={}", userEntitySaved);
        return userEntitySaved;
    }

    @Override
    public UserEntity createRestTemplate(String cep, UserEntity userEntity) {
        String number = getNumber(userEntity);

        Address address = cepService.getCepWithRestTemplate(cep);
        buildEntity(userEntity, number, address);

        UserEntity userEntitySaved = saveUser(userEntity);

        log.info("UserEntity saved={}", userEntitySaved);
        return userEntitySaved;
    }

    private String getNumber(UserEntity userEntity) {
        return userEntity
                .getAddress()
                .getNumber();
    }

    private void buildEntity(UserEntity userEntity, String number, Address address) {
        address.setNumber(number);
        userEntity.setAddress(address);
    }

    private UserEntity saveUser(UserEntity userEntity) {
        return repository.save(userEntity);
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
        List<UserEntity> userEntityList = repository.findByNameContainingOrEmailContainingAllIgnoreCase(name, email);

        log.info("UserEntity found={}", userEntityList);
        return userEntityList;
    }

    @Override
    public List<UserEntity> findByNameAndEmailJPQL(String name, String email) {
        List<UserEntity> userEntityList = repository.findByNameContainingOrEmailContainingAllIgnoreCaseJPQL(name, email);

        log.info("UserEntity found={}", userEntityList);
        return userEntityList;
    }

    @Override
    public List<UserEntity> findByNameAndEmailNativeQuery(String name, String email) {
        List<UserEntity> userEntityList = repository.findByNameContainingOrEmailContainingAllIgnoreCaseNativeQuery(name, email);

        log.info("UserEntity found={}", userEntityList);
        return userEntityList;
    }

    @Override
    public Page<UserEntity> findByNameAndEmailPaginated(Pageable pageable) {
        Page<UserEntity> userEntityPage = repository.findAll(pageable);

        log.info("UserEntity found={}", userEntityPage);
        return userEntityPage;
    }
}