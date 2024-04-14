package com.example.socialnetworkrestapi.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void accessDeniedTest() throws Exception {
        this.mockMvc.perform(get("/home"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/login"));
    }

    @Test
    public void authenticateUserTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .param("name", "1")
                        .param("password", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void unauthenticatedUserTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                        .param("name", "jfighdg")
                        .param("password", "fmasngprair"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    public void postsGetRequestsAccessDeniedTest() throws Exception {
        this.mockMvc.perform(get("/post/**"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void postsPostRequestsAccessDeniedTest() throws Exception {
        this.mockMvc.perform(post("/post/**"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void categoriesGetRequestsAccessDeniedTest() throws Exception {
        this.mockMvc.perform(get("/category/**"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void categoriesPostRequestsAccessDeniedTest() throws Exception {
        this.mockMvc.perform(post("/category/**"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

}