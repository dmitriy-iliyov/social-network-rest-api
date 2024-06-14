package com.example.springpostgresqlrabbitmq.models.DTO.category;

import com.example.springpostgresqlrabbitmq.models.entitys.CategoryEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class CategoryCreatingDTO {

    @NotEmpty(message = "category name shouldn't be empty")
    @Size(min = 1, max = 20, message = "name should be from 1 to 20 characters")
    private String name;

    public static CategoryEntity toEntity(CategoryCreatingDTO categoryDTO){
        return new CategoryEntity(categoryDTO.getName(), Instant.now());
    }
}
