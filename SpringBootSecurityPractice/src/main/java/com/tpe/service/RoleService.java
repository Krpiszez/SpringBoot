package com.tpe.service;

import com.tpe.domain.Role;
import com.tpe.domain.enums.UserRole;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role getRoleType(UserRole roleType) {
        Role role = roleRepository.findByName(roleType)
                .orElseThrow(()-> new ResourceNotFoundException("Role can not be found with name: " + roleType.name()));
        return role;
    }
}
