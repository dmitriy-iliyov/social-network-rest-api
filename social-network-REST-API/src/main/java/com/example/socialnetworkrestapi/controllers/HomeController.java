package com.example.socialnetworkrestapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public String homePage(){
        return "home_page";
    }
}
