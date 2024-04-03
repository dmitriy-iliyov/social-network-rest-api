package com.example.socialnetworkrestapi.DTO;

import com.example.socialnetworkrestapi.entitys.CategoryEntity;
import com.example.socialnetworkrestapi.entitys.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class CategoryDTO {

    private Long id;
    private String name;
    private List<PostEntity> posts;

    public static CategoryDTO toDTO(CategoryEntity categoryEntity){
        return new CategoryDTO(categoryEntity.getId(), categoryEntity.getName(), categoryEntity.getPosts());
    }
}
