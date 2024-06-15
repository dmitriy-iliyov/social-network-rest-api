package com.example.rabbitmq_api.models.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="categories")
public class CategoryEntity {

    @Id
    @SequenceGenerator(name = "categories_sequence", sequenceName = "categories_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "categories_sequence")
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 20, columnDefinition = "TEXT")
    private String name;

    @Column(name = "create_date", nullable = false, columnDefinition = "DATE")
    private Instant createDate;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<PostEntity> posts;

    public CategoryEntity(String name, Instant createDate){
        this.name = name;
        this.createDate = createDate;
    }
}