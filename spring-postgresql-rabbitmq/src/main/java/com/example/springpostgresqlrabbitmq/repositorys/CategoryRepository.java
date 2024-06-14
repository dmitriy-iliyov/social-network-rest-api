package com.example.springpostgresqlrabbitmq.repositorys;

import com.example.springpostgresqlrabbitmq.models.entitys.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByName(String name);
    void deleteByName(String name);
}