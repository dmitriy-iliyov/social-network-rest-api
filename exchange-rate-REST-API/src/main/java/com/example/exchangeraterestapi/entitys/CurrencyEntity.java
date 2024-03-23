package com.example.exchangeraterestapi.entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;

@Data
@Table
@Entity(name="currency's")
public class CurrencyEntity {

    @Id
    private Long id;

}
