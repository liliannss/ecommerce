package br.com.ecommerce.user.services;

import br.com.ecommerce.user.models.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserEntity createWithFeign(String cep, UserEntity userEntity);
    UserEntity createWithWebClient(String cep, UserEntity userEntity);
    UserEntity createWithRestTemplate(String cep, UserEntity userEntity);
    UserEntity findById(Long id);
    List<UserEntity> findByNameAndEmailWithQueryMethods(String name, String email);
    List<UserEntity> findByNameAndEmailWithJPQL(String name, String email);
    List<UserEntity> findByNameAndEmailWithNativeQuery(String name, String email);
    Page<UserEntity> findByNameAndEmailPaginated(Pageable pageable);
    UserEntity update(Long id, UserEntity userEntity);
    void delete(Long id);
}