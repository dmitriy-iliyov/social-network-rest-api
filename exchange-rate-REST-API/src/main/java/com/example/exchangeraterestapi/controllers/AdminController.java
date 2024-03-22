package com.example.exchangeraterestapi.controllers;

import com.example.exchangeraterestapi.entitys.AdminEntity;
import com.example.exchangeraterestapi.services.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/new")
    @ResponseBody
    public String adminRegisterForm(Model model){
        model.addAttribute("admin", new AdminEntity());
        return "admin_register_form";
    }

    @PostMapping("/new")
    @ResponseBody
    public void saveNewAdmin(@ModelAttribute AdminEntity admin){
        adminService.save(admin);
    }
}
