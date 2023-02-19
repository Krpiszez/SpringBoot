package com.tpe.service;

import com.tpe.domain.Role;
import com.tpe.domain.User;
import com.tpe.domain.enums.UserRole;
import com.tpe.dto.RegisterRequest;
import com.tpe.exception.ConflictException;
import com.tpe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void save(RegisterRequest registerRequest) {

        if(userRepository.existsByUserName(registerRequest.getUserName())){
            throw new ConflictException("User with user name: " + registerRequest.getUserName() + " already exists!");
        }
        User user = new User();
        user.setUserName(registerRequest.getUserName());
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        // We are setting encoded password!
        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
        user.setPassword(encodedPassword);
        // Default role of New user will be "Student" role.
        Role role = roleService.getRoleType(UserRole.ROLE_STUDENT);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);

    }
}
