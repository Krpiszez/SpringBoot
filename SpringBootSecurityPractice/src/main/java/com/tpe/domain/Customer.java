package com.tpe.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "t_customer")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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


}
