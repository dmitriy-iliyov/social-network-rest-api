package com.example.exchangeraterestapi.services;

import com.example.exchangeraterestapi.DTO.CurrencyDTO;
import com.example.exchangeraterestapi.entitys.CurrencyEntity;
import com.example.exchangeraterestapi.repositorys.CurrencyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Transactional
    public void save(CurrencyEntity currencyEntity){
        currencyRepository.save(currencyEntity);
    }

    @Transactional
    public Optional<CurrencyDTO> findById(Long id){
        return currencyRepository.findById(id).map(CurrencyDTO::toDTO);
    }

    @Transactional
    public Iterable<CurrencyEntity> findAll(){
        return currencyRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id){
        currencyRepository.deleteById(id);
    }

}
