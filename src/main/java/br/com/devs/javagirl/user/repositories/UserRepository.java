package br.com.devs.javagirl.user.repositories;

import br.com.devs.javagirl.user.models.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByNameContainingOrEmailContainingAllIgnoreCase(String name, String email);

    @Query("SELECT u FROM UserEntity u WHERE u.name LIKE (CONCAT('%',:name,'%')) OR u.email LIKE (CONCAT('%',:email,'%'))")
    List<UserEntity> findByNameContainingOrEmailContainingAllIgnoreCaseJPQL(String name, String email);

    @Query(value = "SELECT * FROM usuario u WHERE (u.name) LIKE %:name% OR (u.email) LIKE %:email%", nativeQuery = true)
    List<UserEntity> findByNameContainingOrEmailContainingAllIgnoreCaseNativeQuery(String name, String email);
}