package com.example.socialnetworkrestapi.controllers;

import com.example.socialnetworkrestapi.DTO.AdminDTO;
import com.example.socialnetworkrestapi.entitys.AdminEntity;
import com.example.socialnetworkrestapi.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/new")
    public String registerNewAdmin(Model model) {
        model.addAttribute("admin", new AdminEntity());

        return "admin_register_form";
    }

    @PostMapping("/new")
    public ResponseEntity<String> saveNewAdmin(@ModelAttribute AdminEntity adminEntity) {
        adminService.save(adminEntity);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Admin successfully created");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<AdminDTO> getAdminById(@PathVariable Long id) {
        Optional<AdminDTO> adminOptional = adminService.findById(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Info", "Getting admin by id");

        return adminOptional
                .map(adminDTO -> ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(adminDTO))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).headers(httpHeaders).body(null));
    }

    @GetMapping("/all")
    public ResponseEntity<Iterable<AdminDTO>> getAllAdmins() {
        Iterable<AdminDTO> admins = adminService.findAll();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Info", "Getting all admin");

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .body(admins);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable("id") Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Info","Deleting admin by id");
        try {
            adminService.deleteById(id);
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .headers(httpHeaders)
                    .body("Admin with ID " + id + " has been successfully deleted");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .headers(httpHeaders)
                    .body("Failed to delete admin with ID " + id);
        }
    }
}
