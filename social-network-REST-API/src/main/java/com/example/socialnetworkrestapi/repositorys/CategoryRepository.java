package com.example.socialnetworkrestapi.repositorys;

import com.example.socialnetworkrestapi.entitys.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByName(String name);
    void deleteByName(String name);
}
