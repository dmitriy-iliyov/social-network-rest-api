package com.example.socialnetworkrestapi.models.entitys;

import com.example.socialnetworkrestapi.models.Role;
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
    private Instant createDate = Instant.now();

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<PostEntity> posts;

    public UserEntity(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = Role.USER;
    }
}
