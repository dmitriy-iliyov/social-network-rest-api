package com.example.exchangeraterestapi.services;

import com.example.exchangeraterestapi.DTO.CategoryDTO;
import com.example.exchangeraterestapi.DTO.UserDTO;
import com.example.exchangeraterestapi.entitys.CategoryEntity;
import com.example.exchangeraterestapi.repositorys.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public void save(CategoryEntity categoryEntity){
        categoryRepository.save(categoryEntity);
    }

    @Transactional
    public Optional<CategoryDTO> findById(Long id){
        return categoryRepository.findById(id).map(CategoryDTO::toDTO);
    }

    @Transactional
    public Optional<CategoryDTO> findByName(String categoryName){
        return categoryRepository.findByName(categoryName).map(CategoryDTO::toDTO);
    }

    @Transactional
    public Iterable<CategoryDTO> findAll(){
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        categoryRepository.findAll().forEach(categoryEntity -> categoryDTOS.add(CategoryDTO.toDTO(categoryEntity)));
        return categoryDTOS;
    }

    @Transactional
    public void deleteById(Long id){
        categoryRepository.deleteById(id);
    }

    @Transactional
    public void deleteByName(String categoryName){
        categoryRepository.deleteByName(categoryName);
    }
}
