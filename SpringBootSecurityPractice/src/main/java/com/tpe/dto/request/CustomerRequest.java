package com.tpe.dto.request;

import com.tpe.domain.User;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

    @NotBlank
    @NotNull
    @Size(min = 2, max = 50, message = "The ${validatedValue} must be between {min} and {max} characters!")
    private String name;

    @NotBlank
    @NotNull
    @Size(min = 2, max = 50, message = "The ${validatedValue} must be between {min} and {max} characters!")
    private String lastName;

    @Email
    private String email;

    private String phone;

    private String userName;


}
