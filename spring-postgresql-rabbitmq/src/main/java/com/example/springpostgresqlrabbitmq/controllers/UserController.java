package com.example.springpostgresqlrabbitmq.controllers;


import com.example.springpostgresqlrabbitmq.models.DTO.user.UserRegistrationDTO;
import com.example.springpostgresqlrabbitmq.models.DTO.user.UserResponseDTO;
import com.example.springpostgresqlrabbitmq.models.entitys.UserEntity;
import com.example.springpostgresqlrabbitmq.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    @GetMapping("/new")
    public String registerNewUserForm(Model model){
        model.addAttribute("user", new UserRegistrationDTO());

        return "user_register_form";
    }

    @PostMapping("/new")
    public ResponseEntity<Object> saveNewUser(@ModelAttribute("user") @Valid UserRegistrationDTO user,
                                         BindingResult bindingResult){

        HttpHeaders httpHeaders = new HttpHeaders();

        if(bindingResult.hasErrors()){
            httpHeaders.setLocation(URI.create("/user/login"));
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .headers(httpHeaders)
                    .body(htmlTemplate);
        }
        try{
            userService.save(user);
            httpHeaders.add("X-Info", "Creating user");
            httpHeaders.setLocation(URI.create("/user/login"));
            return ResponseEntity
                    .status(HttpStatus.SEE_OTHER)
                    .headers(httpHeaders)
                    .body("User successfully created, redirecting...");
        }catch (DataIntegrityViolationException e){
            httpHeaders.add("X-Info", "Creating user failed");
            System.out.println("EXCEPTION  " + e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .headers(httpHeaders)
                    .body("User with name " + user.getName() + " already exists");
        }
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model){
        UserResponseDTO userResponseDTO = userService.findDtoById(id).orElse(null);
        if(userResponseDTO != null){
            model.addAttribute("user", userResponseDTO);
            return "user_edit_form";
        }
        else{
            System.out.println("EXCEPTION  User not found in database!");
            return "redirect:/user/login";
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<String>  saveEditedUser(@ModelAttribute("user") UserResponseDTO userResponseDTO){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-info", "Editing user");

        try {
            userService.update(userResponseDTO);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .headers(httpHeaders)
                    .body("Successfully edited");
        } catch (DataIntegrityViolationException e){
            System.out.println("EXCEPTION  " + e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .headers(httpHeaders)
                    .body("failed(");
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> getUserByIdOrName(@RequestParam(required = false) Long id,
                                               @RequestParam(required = false) String name) {
        HttpHeaders httpHeaders = new HttpHeaders();
        try {
            if(name != null){
                httpHeaders.add("X-Info", "Getting user by name");
                return getResponseEntity(httpHeaders, userService.findEntityByName(name));
            }
            if(id != null){
                httpHeaders.add("X-Info", "Getting user by id");
                return getResponseEntity(httpHeaders, userService.findEntityById(id));
            }
        } catch (NullPointerException e){
            System.out.println("EXCEPTION  " + e.getMessage());
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .headers(httpHeaders)
                .body("NullPointerException, user can't be find");
    }

    private ResponseEntity<?> getResponseEntity(HttpHeaders httpHeaders, Optional<UserEntity> userEntity) {
        UserResponseDTO userResponseDTO = userEntity.map(UserResponseDTO::toDTO).orElse(null);
        if(userResponseDTO != null)
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .headers(httpHeaders)
                    .body(userResponseDTO);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .headers(httpHeaders)
                .body(null);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {

        List<UserResponseDTO> users = userService.findAll();

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
                    .body("User with id " + id + " has been successfully deleted");
        } catch (Exception e) {
            System.out.println("EXCEPTION  " + e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .headers(httpHeaders)
                    .body("Failed to delete user with id " + id + ": " + e.getMessage());
        }
    }
}
