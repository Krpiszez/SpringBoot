package com.tpe.controller;

import com.tpe.dto.request.LoginRequest;
import com.tpe.dto.request.RegisterRequest;
import com.tpe.dto.response.UserResponse;
import com.tpe.security.JwtUtils;
import com.tpe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping
public class UserJwtController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequest registerRequest){

        userService.save(registerRequest);
        String message = "User has been registered successfully!";
        return new ResponseEntity<>(message, HttpStatus.CREATED);

    }

    @PostMapping("/register/admin")
    public ResponseEntity<String> registerAdmin(@Valid @RequestBody RegisterRequest registerRequest){

        userService.saveAdmin(registerRequest);
        String message = "User has been registered successfully!";
        return new ResponseEntity<>(message, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authManager = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUserName(),
                loginRequest.getPassword()
        ));

        String token = jwtUtils.createToken(authManager);
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserResponse> userResponseList = userService.findAllUsers();
        return ResponseEntity.ok(userResponseList);
    }
}
