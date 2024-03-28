package com.example.exchangeraterestapi.entitys;

import jakarta.persistence.*;
import lombok.Data;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Table
@Entity(name="posts")
public class PostEntity {

    @Id
    @SequenceGenerator(name = "post_sequence", sequenceName = "post_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "post_sequence")
    private Long id;

    @Column(name = "topic", nullable = false, columnDefinition = "TEXT")
    private String topic;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    private CategoryEntity categoryEntity;
}
