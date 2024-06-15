package com.example.consumer_service.repositorys;

import com.example.consumer_service.models.entitys.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByName(String name);

    boolean existsUserEntityByName(String name);

    void deleteByName(String name);
}
