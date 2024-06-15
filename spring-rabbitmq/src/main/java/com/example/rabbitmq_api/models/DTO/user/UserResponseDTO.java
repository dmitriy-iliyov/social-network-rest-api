package com.example.rabbitmq_api.models.DTO.user;


import com.example.rabbitmq_api.models.DTO.post.PostResponseDTO;
import com.example.rabbitmq_api.models.entitys.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;
    private Instant createDate;
    private List<PostResponseDTO> posts;


    public static UserResponseDTO toDTO(UserEntity userEntity){
        List<PostResponseDTO> listOfPostDTO = new ArrayList<>();
        userEntity.getPosts().forEach(postEntity -> listOfPostDTO.add(PostResponseDTO.toDTO(postEntity)));

        return new UserResponseDTO(
                userEntity.getId(), userEntity.getName(),
                userEntity.getEmail(), userEntity.getCreateDate(), listOfPostDTO
        );
    }
}
