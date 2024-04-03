package com.example.socialnetworkrestapi.repositorys;

import com.example.socialnetworkrestapi.entitys.PostEntity;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<PostEntity, Long> {

    Iterable<PostEntity> findAllByUserId(Long userID);
    Iterable<PostEntity> findAllByUserName(String name);
    Iterable<PostEntity> findAllByCategoryId(Long categoryID);
    Iterable<PostEntity> findAllByCategoryName(String name);
}
