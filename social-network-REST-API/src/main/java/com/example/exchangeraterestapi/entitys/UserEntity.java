package com.example.exchangeraterestapi.entitys;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.Set;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Data
@Table
@Entity(name="users")
public class UserEntity {

    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "user_sequence")
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @Column(name = "email", nullable = false, unique = true, columnDefinition = "TEXT")
    private String email;

    @Column(name = "create_date", nullable = false, columnDefinition = "DATE")
    private Instant createDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<PostEntity> postEntitySet;
}
