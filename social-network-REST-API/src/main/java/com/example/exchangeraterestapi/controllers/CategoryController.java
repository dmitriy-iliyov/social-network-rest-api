package com.example.exchangeraterestapi.controllers;


import com.example.exchangeraterestapi.DTO.CategoryDTO;
import com.example.exchangeraterestapi.entitys.CategoryEntity;
import com.example.exchangeraterestapi.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/new")
    public String addNewCategory(Model model){
        model.addAttribute("category", new CategoryEntity());

        return "category_register_form";
    }

    @PostMapping("/new")
    public ResponseEntity<String> saveNewCategory(@ModelAttribute CategoryEntity categoryEntity){
        categoryService.save(categoryEntity);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-info", "Creating category");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .headers(httpHeaders)
                .body("Category successfully created");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id){
        Optional<CategoryDTO> categoryOptional = categoryService.findById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-info", "Getting category by id");

        return categoryOptional
                .map(categoryDTO -> ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(categoryDTO))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(null));
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable String name){
        Optional<CategoryDTO> categoryOptional = categoryService.findByName(name);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-info", "Getting category by name");

        return categoryOptional
                .map(categoryDTO -> ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(categoryDTO))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(null));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDTO>> getAllCategories(){
        List<CategoryDTO> categories = categoryService.findAll();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-info", "Getting all category");

        return categories.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(null)
                : ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(categories);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-info", "Deleting category");
        try{
            categoryService.deleteById(id);
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .headers(httpHeaders)
                    .body("Category with ID " + id + " has been successfully deleted");
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .headers(httpHeaders)
                    .body("Failed to delete admin with ID " + id);
        }
    }
}
