package com.example.exchangeraterestapi.entitys;

import jakarta.persistence.*;
import lombok.Data;

import static jakarta.persistence.GenerationType.SEQUENCE;


@Data
@Table
@Entity(name="currencys")
public class CurrencyEntity {

    @Id
    @SequenceGenerator(name = "currency_sequence", sequenceName = "currency_sequence", allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE, generator = "currency_sequence")
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;
}
