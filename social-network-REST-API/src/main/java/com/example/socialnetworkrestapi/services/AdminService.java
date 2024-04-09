package com.example.socialnetworkrestapi.services;

import com.example.socialnetworkrestapi.models.DTO.user.AdminDTO;
import com.example.socialnetworkrestapi.models.entitys.AdminEntity;
import com.example.socialnetworkrestapi.repositorys.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    @Transactional
    public void save(AdminEntity admin) { adminRepository.save(admin); }

    @Transactional
    public Optional<AdminDTO> findById(Long id){
        return adminRepository.findById(id).map(AdminDTO::toDTO);
    }

    @Transactional
    public Iterable<AdminDTO> findAll() {
        List<AdminDTO> adminDTOs = new ArrayList<>();
        adminRepository.findAll().forEach(adminEntity -> adminDTOs.add(AdminDTO.toDTO(adminEntity)));
        return adminDTOs;
    }

    @Transactional
    public void deleteById(Long id){
        adminRepository.deleteById(id);
    }

    @Transactional
    public void deleteByPassword(String password){
        adminRepository.deleteByPassword(password);
    }
}
