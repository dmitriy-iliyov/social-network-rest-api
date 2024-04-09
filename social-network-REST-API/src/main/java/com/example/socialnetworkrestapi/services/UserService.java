package com.example.socialnetworkrestapi.services;

import com.example.socialnetworkrestapi.models.DTO.user.UserResponseDTO;
import com.example.socialnetworkrestapi.models.entitys.UserEntity;
import com.example.socialnetworkrestapi.repositorys.UserRepository;
import com.example.socialnetworkrestapi.security.UserDetailsImplementation;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByName(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
        return UserDetailsImplementation.build(userEntity);
    }

    @Transactional
    public void save(UserEntity userEntity){
        userRepository.save(userEntity);
    }

    @Transactional
    public boolean existingUserEntityByName(String name){
        return userRepository.existsUserEntityByName(name);
    }

    @Transactional
    public Optional<UserEntity> findById(Long id){
        return userRepository.findById(id);
    }

    @Transactional
    public Optional<UserEntity> findByName(String name){
        return userRepository.findByName(name);
    }

    @Transactional
    public List<UserResponseDTO> findAll(){
        List<UserResponseDTO> userDTOS = new ArrayList<>();
        userRepository.findAll().forEach(userEntity -> userDTOS.add(UserResponseDTO.toDTO(userEntity)));
        return userDTOS;
    }

    @Transactional
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }
}
