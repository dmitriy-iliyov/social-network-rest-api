package com.example.socialnetworkrestapi.models.DTO.user;

import com.example.socialnetworkrestapi.models.entitys.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegistrationDTO {

    private String name;
    private String password;
    private String email;

    public static UserEntity toEntity(UserRegistrationDTO userDTO){
        return new UserEntity(userDTO.getName(), userDTO.getPassword(),userDTO.getEmail());
    }
}
