package com.tpe.service;

import com.tpe.domain.Role;
import com.tpe.domain.User;
import com.tpe.domain.enums.UserRole;
import com.tpe.dto.request.RegisterRequest;
import com.tpe.dto.response.UserResponse;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    public void saveCustomer(RegisterRequest registerRequest) {
        if (userRepository.existsByUserName(registerRequest.getUserName())){
            throw new ConflictException("User with user name: " + registerRequest.getUserName() + " already exists!");
        }
        User user = new User();
        user.setUserName(registerRequest.getUserName());
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

    public void saveAdmin(RegisterRequest registerRequest) {
        if (userRepository.existsByUserName(registerRequest.getUserName())){
            throw new ConflictException("User with user name: " + registerRequest.getUserName() + " already exists!");
        }
        User admin = new User();
        admin.setUserName(registerRequest.getUserName());
        admin.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        admin.setFirstName(registerRequest.getFirstName());
        admin.setLastName(registerRequest.getLastName());
        Role role = roleService.getRoleType(UserRole.ROLE_ADMIN);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        admin.setRoles(roles);
        userRepository.save(admin);
    }

    public List<UserResponse> findAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserResponse> userResponseList = new ArrayList<>();

        for (User u: userList){
            UserResponse userResponse =
                    new UserResponse(u.getFirstName(), u.getLastName(), u.getUserName(), u.getRoles());
            userResponseList.add(userResponse);
        }
        return userResponseList;

    }

    public void saveShopOwner(RegisterRequest registerRequest) {
        if (userRepository.existsByUserName(registerRequest.getUserName())){
            throw new ConflictException("User with user name: " + registerRequest.getUserName() + " already exists!");
        }
        User admin = new User();
        admin.setUserName(registerRequest.getUserName());
        admin.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        admin.setFirstName(registerRequest.getFirstName());
        admin.setLastName(registerRequest.getLastName());
        Role role = roleService.getRoleType(UserRole.ROLE_SHOPOWNER);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        admin.setRoles(roles);
        userRepository.save(admin);
    }
}
