package com.tpe.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "First Name can not be Null!")
    @NotBlank(message = "First Name can not be only \"Space Character\"")
    @Size(min = 4, max = 25, message = "First name ${validateValue} must be between {min} and {max} characters!")
    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private Integer grade;

    private LocalDateTime createDate = LocalDateTime.now();


}
