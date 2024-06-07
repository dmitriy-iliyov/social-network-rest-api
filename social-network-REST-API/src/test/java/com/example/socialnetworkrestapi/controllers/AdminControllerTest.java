package com.example.socialnetworkrestapi.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void registerNewAdminTest() throws Exception {
        this.mockMvc.perform(get("/admin/new"))
                .andDo(print())
                .andExpect(view().name("admin_register_form"));
    }

    @Test
    public void saveNewAdminTest() {

    }

//    @Test
//    @WithMockUser(authorities = "ADMIN")
//    public void getTrueAdminByIdTest() {
//        this.mockMvc.perform(get());
//    }

    @Test
    public void getFalseAdminByIdTest(){

    }

    @Test
    void getAllAdminsTest() {
    }

    @Test
    void deleteAdminTest() {
    }
}