package com.example.socialnetworkrestapi.models.DTO.user;

import com.example.socialnetworkrestapi.models.Role;
import com.example.socialnetworkrestapi.models.entitys.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class AdminRegistrationDTO {

    private String name;
    private String password;

    public static UserEntity toEntity(AdminRegistrationDTO adminDTO){
        return UserEntity.builder()
                .name(adminDTO.name)
                .password(adminDTO.password)
                .role(Role.ADMIN)
                .createDate(Instant.now())
                .email("-")
                .build();
    }
}
