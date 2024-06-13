package com.example.springpostgresqlrabbitmq.models.DTO.user;

import com.example.springpostgresqlrabbitmq.models.entitys.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class UserRegistrationDTO{


    @NotEmpty(message = "name shouldn't be empty")
    @Size(min = 1, max = 10, message = "name should be less then 10")
    private String name;
    @NotEmpty(message = "email shouldn't be empty")
    @Email(message = "email should be valid")
    private String email;

    public static UserEntity toEntity(UserRegistrationDTO userDTO){
        return UserEntity.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .createDate(Instant.now())
                .build();
    }
}
