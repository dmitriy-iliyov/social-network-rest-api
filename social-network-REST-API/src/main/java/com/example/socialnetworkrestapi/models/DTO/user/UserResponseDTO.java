package com.example.socialnetworkrestapi.models.DTO.user;


import com.example.socialnetworkrestapi.models.DTO.post.PostResponseDTO;
import com.example.socialnetworkrestapi.models.entitys.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String name;
    private String password;
    private String email;
    private Instant createDate;
    private List<PostResponseDTO> posts;


    public static UserResponseDTO toDTO(UserEntity userEntity){
        List<PostResponseDTO> listOfPostDTO = new ArrayList<>();
        userEntity.getPosts().forEach(postEntity -> listOfPostDTO.add(PostResponseDTO.toDTO(postEntity)));
        return new UserResponseDTO(
                userEntity.getId(), userEntity.getName(), userEntity.getPassword(),
                userEntity.getEmail(), userEntity.getCreateDate(), listOfPostDTO
        );
    }
}
