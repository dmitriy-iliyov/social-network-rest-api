package com.example.socialnetworkrestapi.repositorys;

import com.example.socialnetworkrestapi.entitys.AdminEntity;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<AdminEntity, Long> {

    void deleteByPassword(String password);
}
