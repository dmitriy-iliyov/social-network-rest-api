package com.example.socialnetworkrestapi.DTO;

import com.example.socialnetworkrestapi.entitys.AdminEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AdminDTO {

    private Long id;
    private String name;
    private String password;

    public static AdminDTO toDTO(AdminEntity adminEntity){
        return new AdminDTO(adminEntity.getId(), adminEntity.getName(), adminEntity.getName());
    }
}
