package com.example.exchangeraterestapi.controllers;

import com.example.exchangeraterestapi.DTO.UserDTO;
import com.example.exchangeraterestapi.entitys.UserEntity;
import com.example.exchangeraterestapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/new")
    public String registerNewUser(Model model){
        model.addAttribute("user", new UserEntity());

        return "user_register_form";
    }

    @PostMapping("/new")
    public ResponseEntity<String> saveNewUser(@ModelAttribute UserEntity userEntity){
        userService.save(userEntity);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Info", "User successfully created");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .headers(httpHeaders)
                .body("User successfully created");}

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        Optional<UserDTO> userOptional = userService.findById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Info", "Getting user by id");

        return userOptional
                .map(userDTO -> ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(userDTO))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(null));
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<UserDTO> getUserByName(@PathVariable String name) {
        Optional<UserDTO> userOptional = userService.findByName(name);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Info", "Getting user by name");

        return userOptional
                .map(userDTO -> ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(userDTO))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(null));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.findAll();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Info", "Getting all users");

        return users.isEmpty()
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(null)
                : ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(users);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Info", "Deleting user by id");

        try {
            userService.deleteById(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .headers(httpHeaders)
                    .body("User with ID " + id + " has been successfully deleted");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .headers(httpHeaders)
                    .body("Failed to delete user with ID " + id + ": " + e.getMessage());
        }
    }
}
