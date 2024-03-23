package com.example.exchangeraterestapi.services;

import com.example.exchangeraterestapi.DTO.AdminDTO;
import com.example.exchangeraterestapi.entitys.AdminEntity;
import com.example.exchangeraterestapi.repositorys.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Iterable<AdminEntity> findAll(){
        return adminRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id){
        adminRepository.deleteById(id);
    }

    public AdminDTO toDTO(AdminEntity adminEntity){
        return new AdminDTO(adminEntity.getId(), adminEntity.getName(), adminEntity.getName());
    }
}
