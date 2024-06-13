package com.example.springpostgresqlrabbitmq.models.DTO.post;

import com.example.springpostgresqlrabbitmq.models.entitys.CategoryEntity;
import com.example.springpostgresqlrabbitmq.models.entitys.PostEntity;
import com.example.springpostgresqlrabbitmq.models.entitys.UserEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostCreatingDTO {

    @NotEmpty(message = "topic shouldn't be empty")
    @Size(min = 1, max = 20, message = "topic should be from 1 to 20 characters")
    private String topic;
    @NotEmpty(message = "description shouldn't be empty")
    private String description;
    private Long userID;
    private Long categoryID;

    public static PostEntity toEntity(PostCreatingDTO postDTO, UserEntity user, CategoryEntity category){
        return new PostEntity(postDTO.getTopic(), postDTO.getDescription(), user, category);
    }
}
