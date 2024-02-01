package com.spring.Security.Persistence.Repositories;

import com.spring.Security.Persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "SELECT * FROM user WHERE email = : email", nativeQuery = true) /*query nativa = una query normal tambein para la busqueda podemos usar JPA REPOSITORY*/
    Optional<UserEntity> findByEmail(String email);
}
