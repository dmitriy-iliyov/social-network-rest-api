package com.example.socialnetworkrestapi.DTO;


import com.example.socialnetworkrestapi.entitys.PostEntity;
import com.example.socialnetworkrestapi.entitys.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String password;
    private String email;
    private Instant createDate;
    private int postCount;


    public static UserDTO toDTO(UserEntity userEntity){
        return new UserDTO(userEntity.getId(), userEntity.getName(), userEntity.getPassword(), userEntity.getEmail(), userEntity.getCreateDate(), userEntity.getPosts().size());
    }
}