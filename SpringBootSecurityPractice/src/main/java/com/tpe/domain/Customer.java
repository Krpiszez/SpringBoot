package com.tpe.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "t_customer")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;


}
