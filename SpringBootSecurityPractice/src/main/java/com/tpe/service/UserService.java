package com.tpe.service;

import com.tpe.domain.Role;
import com.tpe.domain.User;
import com.tpe.domain.enums.UserRole;
import com.tpe.dto.request.RegisterRequest;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
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
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    public void save(RegisterRequest registerRequest) {
        if (userRepository.existsByUserName(registerRequest.getUserName())){
            throw new ConflictException("User with user name: " + registerRequest.getUserName() + " already exists!");
        }
        User user = new User();
        user.setUserName(user.getUserName());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        Role role = roleService.getRoleType(UserRole.ROLE_CUSTOMER);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
    }

    public User findUserByName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(()-> new ResourceNotFoundException("No user with name: " + userName + " is found!"));
    }
}
