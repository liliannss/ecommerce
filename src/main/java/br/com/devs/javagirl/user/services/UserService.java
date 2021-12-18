package br.com.devs.javagirl.user.services;

import br.com.devs.javagirl.user.models.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserEntity createFeign(String cep, UserEntity userEntity);
    UserEntity createWebClient(String cep, UserEntity userEntity);
    UserEntity createRestTemplate(String cep, UserEntity userEntity);
    UserEntity findById(Long id);
    List<UserEntity> findByNameAndEmailQueryMethods(String name, String email);
    List<UserEntity> findByNameAndEmailJPQL(String name, String email);
    List<UserEntity> findByNameAndEmailNativeQuery(String name, String email);
    Page<UserEntity> findByNameAndEmailPaginated(Pageable pageable);
}