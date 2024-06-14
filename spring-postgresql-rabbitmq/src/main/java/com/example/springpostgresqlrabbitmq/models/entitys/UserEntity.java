package com.example.springpostgresqlrabbitmq.models.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name="users")
public class UserEntity {

    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "user_sequence")
    private Long id;

    @Column(name = "name", nullable = false, length = 10)
    private String name;

    @Column(name = "email", nullable = false, unique = true, columnDefinition = "TEXT")
    private String email;

    @Column(name = "create_date", nullable = false, columnDefinition = "DATE")
    private Instant createDate;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<PostEntity> posts;
}