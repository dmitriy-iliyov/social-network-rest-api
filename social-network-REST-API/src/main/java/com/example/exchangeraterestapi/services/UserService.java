package com.example.exchangeraterestapi.services;

import com.example.exchangeraterestapi.DTO.UserDTO;
import com.example.exchangeraterestapi.entitys.UserEntity;
import com.example.exchangeraterestapi.repositorys.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void save(UserEntity userEntity){
        userRepository.save(userEntity);
    }

    @Transactional
    public Optional<UserDTO> findById(Long id){
        return userRepository.findById(id).map(UserDTO::toDTO);
    }

    @Transactional
    public Optional<UserDTO> findByName(String name){
        return userRepository.findByName(name).map(UserDTO::toDTO);
    }

    @Transactional
    public List<UserDTO> findAll(){
        List<UserDTO> userDTOS = new ArrayList<>();
        userRepository.findAll().forEach(userEntity -> userDTOS.add(UserDTO.toDTO(userEntity)));
        return userDTOS;
    }

    @Transactional
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

}
