package com.example.exchangeraterestapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class CurrencyController {

    @GetMapping("/new")
    public String addNewCurrency(){

    }

    @PostMapping("/new")
    public void saveNewCurrency(){

    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCurrency(@PathVariable String id){

    }

}
