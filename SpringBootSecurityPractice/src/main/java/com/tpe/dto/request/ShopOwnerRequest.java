package com.tpe.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class ShopOwnerRequest {

    @NotBlank(message = "Name cannot be blank!")
    @NotNull(message = "Please enter a value for name!")
    @Size(min = 2, max = 50, message = "The ${validatedValue} must be between {min} and {max} characters!")
    private String name;

    @NotBlank(message = "Last Name cannot be blank!")
    @NotNull(message = "Please enter a value for last name!")
    @Size(min = 2, max = 50, message = "The ${validatedValue} must be between {min} and {max} characters!")
    private String lastName;

    @Email
    private String email;

    private String phone;
    @NotBlank(message = "Last Name cannot be blank!")
    @NotNull(message = "Please enter a value for last name!")
    @Size(min = 2, max = 50, message = "The ${validatedValue} must be between {min} and {max} characters!")
    @Column(unique = true)
    private String shopName;

    private String userName;
}
