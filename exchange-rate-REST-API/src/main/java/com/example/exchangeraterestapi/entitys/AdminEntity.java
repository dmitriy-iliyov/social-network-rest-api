package com.example.exchangeraterestapi.entitys;

import jakarta.persistence.*;
import lombok.Data;

import static jakarta.persistence.GenerationType.SEQUENCE;


@Data
@Table
@Entity(name="admins")
public class AdminEntity {

    @Id
    @SequenceGenerator(name = "admin_sequence", sequenceName = "admin_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "admin_sequence")
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;
}
