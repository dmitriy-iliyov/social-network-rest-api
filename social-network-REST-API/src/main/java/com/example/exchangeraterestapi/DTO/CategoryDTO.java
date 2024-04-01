package com.example.exchangeraterestapi.DTO;

import com.example.exchangeraterestapi.entitys.CategoryEntity;
import com.example.exchangeraterestapi.entitys.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;


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
