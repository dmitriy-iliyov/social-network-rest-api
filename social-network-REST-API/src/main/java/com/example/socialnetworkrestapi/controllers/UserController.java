package com.example.socialnetworkrestapi.controllers;

import com.example.socialnetworkrestapi.DTO.UserDTO;
import com.example.socialnetworkrestapi.entitys.Role;
import com.example.socialnetworkrestapi.entitys.UserEntity;
import com.example.socialnetworkrestapi.security.JwtCore;
import com.example.socialnetworkrestapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtCore jwtCore;

    @GetMapping("/new")
    public String registerNewUser(Model model){
        model.addAttribute("user", new UserEntity());

        return "user_register_form";
    }

    @PostMapping("/new")
    public ResponseEntity<String> saveNewUser(@ModelAttribute UserEntity userEntity){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Info", "Creating user");
        userEntity.setRole(Role.USER);
        try{
            userService.save(userEntity);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .headers(httpHeaders)
                    .body("User successfully created");
        }catch (DataIntegrityViolationException exception){
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .headers(httpHeaders)
                    .body("User with name " + userEntity.getName() + " is exist");
        }
    }

    @GetMapping("/get")
    public ResponseEntity<UserDTO> getUserByIdOrName(@RequestParam(required = false) String name, @RequestParam(required = false) Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Info", "Getting user");

        if(name != null){
            Optional<UserDTO> userOptional = userService.findByName(name);

            return userOptional
                    .map(userDTO -> ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(userDTO))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(null));
        }else if(id != null){
            Optional<UserDTO> userOptional = userService.findById(id);

            return userOptional
                    .map(userDTO -> ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(userDTO))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(null));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(httpHeaders).body(null);
        }
    }

    @GetMapping("/all")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
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
