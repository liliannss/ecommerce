package br.com.ecommerce.user.repositories;

import br.com.ecommerce.user.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByNameContainingOrEmailContainingAllIgnoreCase(String name, String email);

    @Query("SELECT u FROM UserEntity u WHERE u.name LIKE (CONCAT('%',:name,'%')) OR u.email LIKE (CONCAT('%',:email,'%'))")
    List<UserEntity> findByNameContainingOrEmailContainingAllIgnoreCaseJPQL(String name, String email);

    @Query(value = "SELECT * FROM usuario u WHERE (u.nome) LIKE %:nome% OR (u.email) LIKE %:email%", nativeQuery = true)
    List<UserEntity> findByNameContainingOrEmailContainingAllIgnoreCaseNativeQuery(@Param("nome") String name, String email);
}