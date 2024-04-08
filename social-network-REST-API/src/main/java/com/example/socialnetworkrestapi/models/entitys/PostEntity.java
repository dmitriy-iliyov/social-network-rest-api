package com.example.socialnetworkrestapi.models.entitys;

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

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private CategoryEntity category;
}
