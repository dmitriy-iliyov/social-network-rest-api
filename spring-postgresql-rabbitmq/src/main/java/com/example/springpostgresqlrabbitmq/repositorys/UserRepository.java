package com.example.springpostgresqlrabbitmq.repositorys;

import com.example.springpostgresqlrabbitmq.models.entitys.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByName(String name);

    boolean existsUserEntityByName(String name);

    void deleteByName(String name);
}
