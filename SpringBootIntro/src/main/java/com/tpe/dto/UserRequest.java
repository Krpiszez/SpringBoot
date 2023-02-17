package com.tpe.dto;

import com.tpe.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @Column(length = 25, nullable = false)
    private String firstName;
    @Column(length = 25, nullable = false)
    private String lastName;
    @Column(length = 25, nullable = false, unique = true)
    @Size(min = 4, max = 25, message = "Username must be between {min} and {max} characters!")
    private String userName;
    @Column(length = 255, nullable = false)
    private String password;


}
