package br.com.devs.javagirl.user.services;

import br.com.devs.javagirl.user.models.Address;
import br.com.devs.javagirl.user.models.UserEntity;
import br.com.devs.javagirl.user.repositories.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    //TODO adicionar log

    private final UserRepository repository;
    private final CepServiceImpl cepService;

    @Override
    public UserEntity createFeign(String cep, UserEntity userEntity) {
        Address address = getAddress(cep);
        userEntity.setAddress(address);

        return repository.save(userEntity);
    }

    @Override
    public UserEntity createWebClient(String cep, UserEntity userEntity) {
        Address address = cepService.getCepWithWebClient(cep);
        userEntity.setAddress(address);

        return repository.save(userEntity);
    }

    @Override
    public UserEntity createRestTemplate(String cep, UserEntity userEntity) {
        Address address = cepService.getCepWithRestTemplate(cep);
        userEntity.setAddress(address);

        return repository.save(userEntity);
    }

    private Address getAddress(String cep) {
        return cepService.getCepWithFeign(cep);
    }

    @Override
    public UserEntity findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "user.not.found"));
    }

    @Override
    public List<UserEntity> findByNameAndEmailQueryMethods(String name, String email) {
        return repository.findByNameContainingOrEmailContainingAllIgnoreCase(name, email);
    }

    @Override
    public List<UserEntity> findByNameAndEmailJPQL(String name, String email) {
        return repository.findByNameContainingOrEmailContainingAllIgnoreCaseJPQL(name, email);
    }

    @Override
    public List<UserEntity> findByNameAndEmailNativeQuery(String name, String email) {
        return repository.findByNameContainingOrEmailContainingAllIgnoreCaseNativeQuery(name, email);
    }

}