package com.example.springpostgresqlrabbitmq.services;


import com.example.springpostgresqlrabbitmq.models.DTO.user.UserRegistrationDTO;
import com.example.springpostgresqlrabbitmq.models.DTO.user.UserResponseDTO;
import com.example.springpostgresqlrabbitmq.models.entitys.UserEntity;
import com.example.springpostgresqlrabbitmq.repositorys.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService{

    private final UserRepository userRepository;


    @Transactional
    public void save(UserRegistrationDTO userRegistrationDTO){
        UserEntity userEntity = UserRegistrationDTO.toEntity(userRegistrationDTO);
        userRepository.save(userEntity);
    }

    @Transactional
    public boolean existingByName(String name){
        return userRepository.existsUserEntityByName(name);
    }

    @Transactional
    public Optional<UserEntity> findEntityById(Long id){
        return userRepository.findById(id);
    }

    @Transactional
    public Optional<UserEntity> findEntityByName(String name){
        return userRepository.findByName(name);
    }

    @Transactional
    public Optional<UserResponseDTO> findDtoById(Long id){
        return userRepository.findById(id).map(UserResponseDTO::toDTO);
    }

    @Transactional
    public Optional<UserResponseDTO> findDtoByName(String name){
        return userRepository.findByName(name).map(UserResponseDTO::toDTO);
    }

    @Transactional
    public List<UserResponseDTO> findAll(){
        List<UserResponseDTO> users = new ArrayList<>();
        userRepository.findAll().forEach(userEntity -> users.add(UserResponseDTO.toDTO(userEntity)));
        return  users;
    }

    @Transactional
    public void update(UserResponseDTO userResponseDTO){
        userRepository.findById(userResponseDTO.getId())
                .ifPresent(userEntity -> {
                    userEntity.setName(userResponseDTO.getName());
                    userEntity.setEmail(userResponseDTO.getEmail());
                    userRepository.save(userEntity);
                });
    }

    @Transactional
    public  void deleteByName(String name){
        userRepository.deleteByName(name);
    }

    @Transactional
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }
}
