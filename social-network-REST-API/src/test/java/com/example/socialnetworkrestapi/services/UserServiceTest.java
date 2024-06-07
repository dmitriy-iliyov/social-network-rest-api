//package com.example.socialnetworkrestapi.services;
//
//import com.example.socialnetworkrestapi.models.DTO.user.AdminResponseDTO;
//import com.example.socialnetworkrestapi.models.DTO.user.UserResponseDTO;
//import com.example.socialnetworkrestapi.models.Role;
//import com.example.socialnetworkrestapi.models.entitys.UserEntity;
//import com.example.socialnetworkrestapi.repositorys.UserRepository;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//public class UserServiceTest {
//
//    @Autowired
//    private UserService userService;
//
//    @MockBean
//    private UserRepository userRepository;
//
//    @Test
//    public void saveTest(){
//        userRepository.save(userEntity);
//    }
//
//    @Test
//    public void existingByNameTest(){
//
//    }
//
//    @Test
//    public void findUserEntityByIdTest(){
//        UserEntity sampleUser = new UserEntity();
//        sampleUser.setId(1L);
//        sampleUser.setName("john_doe");
//        sampleUser.setEmail("john@example.com");
//
//        when(userRepository.findByName("john_doe")).thenReturn(Optional.of(sampleUser));
//
//        UserEntity foundUser = userService.findEntityByName("john_doe").orElse(null);
//
//        assertThat(foundUser).isNotNull();
//        assertThat(foundUser.getName()).isEqualTo("john_doe");
//        assertThat(foundUser.getEmail()).isEqualTo("john@example.com");
//    }
//
////    @Test
////    public void findUserEntityByNameTest(){
////        return userRepository.findByName(name);
////    }
//
//    @Test
//    public void findDtoById(Long id){
//        return userRepository.findById(id).map(UserResponseDTO::toDTO);
//    }
//
//    @Test
//    public void findDtoByName(String name){
//        return userRepository.findByName(name).map(UserResponseDTO::toDTO);
//    }
//
//    @Test
//    public void findAllUsers(){
//        List<UserResponseDTO> users = new ArrayList<>();
//        userRepository.findAllByRole(Role.USER).forEach(userEntity -> users.add(UserResponseDTO.toDTO(userEntity)));
//        return  users;
//    }
//
//    @Test
//    public void findAllAdmins(){
//        List<AdminResponseDTO> admins = new ArrayList<>();
//        userRepository.findAllByRole(Role.ADMIN).forEach(userEntity -> admins.add(AdminResponseDTO.toDTO(userEntity)));
//        return admins;
//    }
//
//    @Test
//    public void update(){
//        userRepository.findById(userResponseDTO.getId())
//                .ifPresent(userEntity -> {
//                    userEntity.setName(userResponseDTO.getName());
//                    if (!userResponseDTO.getPassword().isEmpty())
//                        userEntity.setPassword(passwordEncoder.encode(userResponseDTO.getPassword()));
//                    userEntity.setEmail(userResponseDTO.getEmail());
//                    userRepository.save(userEntity);
//                });
//    }
//
//    @Test
//    public void deleteByNameAndPassword(){
//        userRepository.deleteByNameAndPassword(name, password);
//    }
//
//    @Test
//    public void deleteById(){
//        userRepository.deleteById(id);
//    }
//}
