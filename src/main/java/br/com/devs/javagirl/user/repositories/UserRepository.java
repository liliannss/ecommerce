package br.com.devs.javagirl.user.repositories;

import br.com.devs.javagirl.user.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByNameOrEmailContainingAllIgnoreCase(String name, String email);
}