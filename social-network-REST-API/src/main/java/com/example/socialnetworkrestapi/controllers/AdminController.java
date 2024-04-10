package com.example.socialnetworkrestapi.controllers;

import com.example.socialnetworkrestapi.models.DTO.user.AdminRegistrationDTO;
import com.example.socialnetworkrestapi.models.DTO.user.AdminResponseDTO;
import com.example.socialnetworkrestapi.models.DTO.user.UserLogInDTO;
import com.example.socialnetworkrestapi.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @GetMapping("/new")
    public String registerNewAdmin(Model model) {
        model.addAttribute("admin", new AdminRegistrationDTO());

        return "admin_register_form";
    }

    @PostMapping("/new")
    public ResponseEntity<String> saveNewAdmin(@ModelAttribute AdminRegistrationDTO admin) {
        userService.save(AdminRegistrationDTO.toEntity(admin));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Admin successfully created");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AdminResponseDTO> getAdminById(@PathVariable Long id) {

        Optional<AdminResponseDTO> adminOptional = userService.findUserEntityById(id).map(AdminResponseDTO::toDTO);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Info", "Getting admin by id");

        return adminOptional
                .map(adminDTO -> ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(adminDTO))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(null));
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<AdminResponseDTO>> getAllAdmins() {

        List<AdminResponseDTO> admins = userService.findAllAdmins();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Info", "Getting all admin");

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(admins);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAdmin(@RequestBody UserLogInDTO admin ) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Info","Deleting admin by password");
        String adminName = admin.getName();

        try {
            userService.deleteByNameAndPassword(adminName, admin.getPassword());
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .headers(httpHeaders)
                    .body("Admin with name " + adminName + " has been successfully deleted");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .headers(httpHeaders)
                    .body("Failed to delete admin with name " + adminName);
        }
    }
}
