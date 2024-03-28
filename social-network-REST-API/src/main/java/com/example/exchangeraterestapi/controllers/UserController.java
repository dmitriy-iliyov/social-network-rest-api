package com.example.exchangeraterestapi.controllers;

import com.example.exchangeraterestapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/currency")
public class UserController {

    private final UserService currencyService;

    @GetMapping("/new")
    public String addNewUser(){

    }

    @PostMapping("/new")
    public void saveNewUser(){

    }

    @GetMapping("/get/{id}")
    public void getUser(@PathVariable String id){

    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable String id){

    }

}
