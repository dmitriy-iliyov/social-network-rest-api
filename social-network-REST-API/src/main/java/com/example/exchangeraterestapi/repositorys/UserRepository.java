package com.example.exchangeraterestapi.repositorys;

import com.example.exchangeraterestapi.entitys.UserEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByName(String name);
}
