package com.example.consumer_service.models.DTO.post;

import com.example.consumer_service.models.entitys.CategoryEntity;
import com.example.consumer_service.models.entitys.PostEntity;
import com.example.consumer_service.models.entitys.UserEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class PostCreatingDTO {

    @NotEmpty(message = "topic shouldn't be empty")
    @Size(min = 1, max = 20, message = "topic should be from 1 to 20 characters")
    private String topic;
    @NotEmpty(message = "description shouldn't be empty")
    private String description;
    @NotEmpty(message = "user id shouldn't be empty")
    private Long userId;
    private Long categoryId;

    public static PostEntity toEntity(PostCreatingDTO postDTO, UserEntity user, CategoryEntity category){
        return new PostEntity(Instant.now(), postDTO.getTopic(), postDTO.getDescription(), user, category);
    }
}
