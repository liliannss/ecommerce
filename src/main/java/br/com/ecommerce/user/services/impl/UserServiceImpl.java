package br.com.ecommerce.user.services.impl;

import br.com.ecommerce.user.models.Address;
import br.com.ecommerce.user.models.UserEntity;
import br.com.ecommerce.user.repositories.UserRepository;
import br.com.ecommerce.user.services.UserService;
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
    public UserEntity createWithFeign(String cep, UserEntity userEntity) {
        String number = getNumber(userEntity);

        Address address = getAddress(cep);
        buildEntity(userEntity, number, address);

        UserEntity userEntitySaved = saveUser(userEntity);

        log.info("User saved={}", userEntitySaved);
        return userEntitySaved;
    }

    private Address getAddress(String cep) {
        return cepService.getAddressWithFeignByCep(cep);
    }

    @Override
    public UserEntity createWithWebClient(String cep, UserEntity userEntity) {
        String number = getNumber(userEntity);

        Address address = cepService.getAddressWithWebClientByCep(cep);
        buildEntity(userEntity, number, address);

        UserEntity userEntitySaved = saveUser(userEntity);

        log.info("User saved={}", userEntitySaved);
        return userEntitySaved;
    }

    @Override
    public UserEntity createWithRestTemplate(String cep, UserEntity userEntity) {
        String number = getNumber(userEntity);

        Address address = cepService.getAddressWithRestTemplateByCep(cep);
        buildEntity(userEntity, number, address);

        UserEntity userEntitySaved = saveUser(userEntity);

        log.info("User saved={}", userEntitySaved);
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

        log.info("User found={}", userEntity);
        return userEntity;
    }

    @Override
    public List<UserEntity> findByNameAndEmailWithQueryMethods(String name, String email) {
        List<UserEntity> userEntityList = repository.findByNameContainingOrEmailContainingAllIgnoreCase(name, email);

        log.info("Users found={}", userEntityList);
        return userEntityList;
    }

    @Override
    public List<UserEntity> findByNameAndEmailWithJPQL(String name, String email) {
        List<UserEntity> userEntityList = repository.findByNameContainingOrEmailContainingAllIgnoreCaseJPQL(name, email);

        log.info("Users found={}", userEntityList);
        return userEntityList;
    }

    @Override
    public List<UserEntity> findByNameAndEmailWithNativeQuery(String name, String email) {
        List<UserEntity> userEntityList = repository.findByNameContainingOrEmailContainingAllIgnoreCaseNativeQuery(name, email);

        log.info("Users found={}", userEntityList);
        return userEntityList;
    }

    @Override
    public Page<UserEntity> findByNameAndEmailPaginated(Pageable pageable) {
        Page<UserEntity> userEntityPaged = repository.findAll(pageable);

        log.info("Users found={}", userEntityPaged);
        return userEntityPaged;
    }

    @Override
    public UserEntity update(Long id, UserEntity userEntity) {
        UserEntity userEntityFound = findById(id);

        userEntityFound.setName(userEntity.getName());
        userEntityFound.setBirthdate(userEntity.getBirthdate());
        userEntityFound.setRg(userEntity.getRg());
        userEntityFound.setCpf(userEntity.getCpf());
        userEntityFound.setPhone(userEntity.getPhone());
        userEntityFound.setCellPhone(userEntity.getCellPhone());
        userEntityFound.setEmail(userEntity.getEmail());
        userEntityFound.setAddress(userEntity.getAddress());

        UserEntity userUpdated = repository.save(userEntityFound);

        log.info("User updated={}", userUpdated);
        return userUpdated;
    }

    @Override
    public void delete(Long id) {
        findById(id);

        repository.deleteById(id);
        log.info("User deleted={}", id);
    }
}