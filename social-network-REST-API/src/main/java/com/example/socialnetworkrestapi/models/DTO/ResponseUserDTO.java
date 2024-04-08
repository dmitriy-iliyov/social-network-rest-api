package com.example.socialnetworkrestapi.models.DTO;


import com.example.socialnetworkrestapi.models.entitys.Role;
import com.example.socialnetworkrestapi.models.entitys.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class ResponseUserDTO{

    private Long id;
    private String name;
    private Role role;
    private String password;
    private String email;
    private Instant createDate;
    private int postCount;


    public static ResponseUserDTO toDTO(UserEntity userEntity){
        return new ResponseUserDTO(userEntity.getId(), userEntity.getName(), userEntity.getRole(), userEntity.getPassword(), userEntity.getEmail(), userEntity.getCreateDate(), userEntity.getPosts().size());
    }
}
