package com.example.exchangeraterestapi.repositorys;

import com.example.exchangeraterestapi.entitys.CategoryEntity;
import com.example.exchangeraterestapi.entitys.PostEntity;
import com.example.exchangeraterestapi.entitys.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<PostEntity, Long> {

    Iterable<PostEntity> findAllByCategoryEntity(CategoryEntity categoryEntity);
    Iterable<PostEntity> findAllByUserEntity(UserEntity userEntity);
}
