package com.example.socialnetworkrestapi.models.DTO.category;

import com.example.socialnetworkrestapi.models.entitys.CategoryEntity;
import com.example.socialnetworkrestapi.models.entitys.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryResponseDTO {

    private Long id;
    private String name;
    private List<PostEntity> posts;

    public static CategoryResponseDTO toDTO(CategoryEntity categoryEntity){
        return new CategoryResponseDTO(categoryEntity.getId(), categoryEntity.getName(), categoryEntity.getPosts());
    }
}
