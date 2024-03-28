package com.example.exchangeraterestapi.DTO;


import com.example.exchangeraterestapi.entitys.PostEntity;
import com.example.exchangeraterestapi.entitys.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.Set;

@Data
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String password;
    private String email;
    private Instant createDate;
    private Set<PostEntity> postEntitySet;


    public static UserDTO toDTO(UserEntity userEntity){
        return new UserDTO(userEntity.getId(), userEntity.getName(), userEntity.getPassword(), userEntity.getEmail(), userEntity.getCreateDate(), userEntity.getPostEntitySet());
    }
}
