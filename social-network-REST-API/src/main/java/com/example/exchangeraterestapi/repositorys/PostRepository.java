package com.example.exchangeraterestapi.repositorys;

import com.example.exchangeraterestapi.entitys.CategoryEntity;
import com.example.exchangeraterestapi.entitys.PostEntity;
import com.example.exchangeraterestapi.entitys.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<PostEntity, Long> {

    Iterable<PostEntity> findAllByUserId(Long userID);
    Iterable<PostEntity> findAllByUserName(String name);
    Iterable<PostEntity> findAllByCategoryId(Long categoryID);
    Iterable<PostEntity> findAllByCategoryName(String name);
}
