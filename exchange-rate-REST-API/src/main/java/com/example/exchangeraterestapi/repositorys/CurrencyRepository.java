package com.example.exchangeraterestapi.repositorys;

import com.example.exchangeraterestapi.entitys.CurrencyEntity;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepository extends CrudRepository<CurrencyEntity, Long> {
}
