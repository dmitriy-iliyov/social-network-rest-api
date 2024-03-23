package com.example.exchangeraterestapi.DTO;


import com.example.exchangeraterestapi.entitys.CurrencyEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrencyDTO {

    private Long id;
    private String name;

    public static CurrencyDTO toDTO(CurrencyEntity currencyEntity){
        return new CurrencyDTO(currencyEntity.getId(), currencyEntity.getName());
    }
}
