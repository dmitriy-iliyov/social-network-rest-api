package com.example.socialnetworkrestapi.controllers;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
class UserControllerTest {

    private final MockMvc mockMvc;
    private final UserController userController;


//    @Test
////    @BeforeAll
//    public void existingTest(){
//        assertThat(userController).isNotNull();
//    }

    @Test
    public void existingTest() throws Exception {
        this.mockMvc.perform(get("/user/login"))
                .andDo(print())
                .andExpect(status().isOk());
    }

//    @BeforeAll
//    public static void existingTest(UserController userController){
//        assertThat(userController).isNotNull();
//    }

//    @Test
//    void registerNewUserForm() {
//        existingTest(userController);
//    }
//
//    @Test
//    void saveNewUser() {
//    }
//
//    @Test
//    void loggingUserForm() {
//    }
//
//    @Test
//    void authenticateUser() {
//    }
//
//    @Test
//    void editUserForm() {
//    }
//
//    @Test
//    void saveEditedUser() {
//    }
//
//    @Test
//    void getUserByIdOrName() {
//    }
//
//    @Test
//    void getAllUsers() {
//    }
//
//    @Test
//    void deleteUser() {
//    }
}