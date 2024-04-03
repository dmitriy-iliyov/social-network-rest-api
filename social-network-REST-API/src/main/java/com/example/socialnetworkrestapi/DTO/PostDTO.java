package com.example.socialnetworkrestapi.DTO;

import com.example.socialnetworkrestapi.entitys.CategoryEntity;
import com.example.socialnetworkrestapi.entitys.PostEntity;
import com.example.socialnetworkrestapi.entitys.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostDTO {

    private Long id;
    private String topic;
    private String description;
    private UserEntity userEntity;
    private CategoryEntity categoryEntity;

    public static PostDTO toDTO(PostEntity postEntity){
        return new PostDTO(postEntity.getId(), postEntity.getTopic(), postEntity.getDescription(),postEntity.getUser(), postEntity.getCategory());
    }
}
