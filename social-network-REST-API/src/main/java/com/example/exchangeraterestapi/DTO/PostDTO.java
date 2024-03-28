package com.example.exchangeraterestapi.DTO;

import com.example.exchangeraterestapi.entitys.CategoryEntity;
import com.example.exchangeraterestapi.entitys.PostEntity;
import com.example.exchangeraterestapi.entitys.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostDTO {

    private Long id;
    private String topic;
    private UserEntity userEntity;
    private CategoryEntity categoryEntity;

    public static PostDTO toDTO(PostEntity postEntity){
        return new PostDTO(postEntity.getId(), postEntity.getTopic(), postEntity.getUserEntity(), postEntity.getCategoryEntity());
    }
}
