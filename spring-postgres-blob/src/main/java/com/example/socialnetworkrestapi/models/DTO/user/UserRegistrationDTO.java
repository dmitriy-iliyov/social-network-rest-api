package com.example.socialnetworkrestapi.models.DTO.user;

import com.example.socialnetworkrestapi.models.Role;
import com.example.socialnetworkrestapi.models.entitys.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class UserRegistrationDTO {

    private String name;
    private String password;
    private String email;

    public static UserEntity toEntity(UserRegistrationDTO userDTO){
        return UserEntity.builder()
                .name(userDTO.name)
                .password(userDTO.password)
                .email(userDTO.email)
                .role(Role.USER)
                .createDate(Instant.now())
                .build();
    }
}
