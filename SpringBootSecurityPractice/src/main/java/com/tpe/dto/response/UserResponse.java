package com.tpe.dto.response;

import com.tpe.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class UserResponse {

    private String firstName;

    private String lastName;

    private String userName;

    private Set<Role> roleSet;


}
