package com.tpe.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data // here a lot of annotation from Lombok dependency is kept inside @Data annotation
public class RegisterRequest {


    @NotBlank(message="Please Provide FirstName")
    @NotNull
    @Size(min=1, max=15, message = "First name '${validatedValue}' must be between {min} and {max} chars long")
    private String firstName;

    @NotBlank(message="Please Provide LastName")
    @NotNull
    @Size(min=1, max=15, message = "Last name '${validatedValue}' must be between {min} and {max} chars long")
    private String lastName;

    @NotBlank(message="Please Provide UserName")
    @NotNull
    @Size(min=3, max=20, message = "User name '${validatedValue}' must be between {min} and {max} chars long")
    private String userName;

    @NotBlank(message="Please Provide Password")
    @NotNull
    @Size(min=5, max=20, message = "Password '${validatedValue}' must be between {min} and {max} chars long")
    private String password;


}