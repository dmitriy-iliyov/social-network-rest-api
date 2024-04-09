package com.example.socialnetworkrestapi.controllers;

import com.example.socialnetworkrestapi.models.DTO.user.UserLogInDTO;
import com.example.socialnetworkrestapi.models.DTO.user.UserResponseDTO;
import com.example.socialnetworkrestapi.models.DTO.user.UserRegistrationDTO;
import com.example.socialnetworkrestapi.security.JwtCore;
import com.example.socialnetworkrestapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public String registerNewUserForm(Model model){
        model.addAttribute("user", new UserRegistrationDTO());

        return "user_register_form";
    }

    @PostMapping("/new")
    public ResponseEntity<String> saveNewUser(@ModelAttribute UserRegistrationDTO user){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Info", "Creating user");

        String encodedUserPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedUserPassword);

        try{
            userService.save(UserRegistrationDTO.toEntity(user));
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .headers(httpHeaders)
                    .body("User successfully created");
        }catch (DataIntegrityViolationException exception){
            return ResponseEntity
                    .status(HttpStatus.SEE_OTHER)
                    .headers(httpHeaders)
                    .location(URI.create("/user/auth"))
                    .body("User with name " + user.getName() + " already exists");
        }
    }

    @GetMapping("/auth")
    public String loggingUserForm(Model model){
        model.addAttribute("user", new UserLogInDTO());

        return "user_signing_in_form";
    }

    @PostMapping("/auth")
    public ResponseEntity<String> authenticateUser(@ModelAttribute UserLogInDTO user){


        Authentication authentication;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Authenticate user");

        try{
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));
            System.out.println(authentication);
            System.out.println(authentication.getDetails());
            System.out.println(authentication.getPrincipal());
        } catch (BadCredentialsException exception){
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .headers(httpHeaders)
                    .body("Incorrect password");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtCore.generateToken(authentication);
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(jwt);
    }

    @GetMapping("/get")
    public ResponseEntity<UserResponseDTO> getUserByIdOrName(@RequestParam(required = false) String name, @RequestParam(required = false) Long id) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Info", "Getting user");

        if(name != null){
            Optional<UserResponseDTO> userOptional = userService.findByName(name).map(UserResponseDTO::toDTO);
            return userOptional
                    .map(userDTO -> ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(userDTO))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(null));
        }else if(id != null){
            Optional<UserResponseDTO> userOptional = userService.findById(id).map(UserResponseDTO::toDTO);
            return userOptional
                    .map(userDTO -> ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(userDTO))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(null));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).headers(httpHeaders).body(null);
        }
    }

    @GetMapping("/all")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
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
                    .body("User with ID " + id + " has been successfully deleted");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .headers(httpHeaders)
                    .body("Failed to delete user with ID " + id + ": " + e.getMessage());
        }
    }
}
