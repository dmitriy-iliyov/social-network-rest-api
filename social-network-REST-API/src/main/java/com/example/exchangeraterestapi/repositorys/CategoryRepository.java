package com.example.exchangeraterestapi.repositorys;

import com.example.exchangeraterestapi.entitys.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByName(String name);
    void deleteByName(String name);
}
