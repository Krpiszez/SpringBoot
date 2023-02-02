package com.tpe.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter // This getter and setter annotations from Lombok handle the getter-setter methods.
@AllArgsConstructor // This Annotation grants you a full variable constructor from this class.
@NoArgsConstructor // This Annotation grants you a non-variable constructor from this class.
@Entity
@Table(name = "t_student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "First Name can not be Null!")
    @NotBlank(message = "First Name can not be only \"Space Character\"")
    @Size(min = 4, max = 25, message = "First name ${validateValue} must be between {min} and {max} characters!")
    @Column(nullable = false, length = 25)//here it checks in DB but in validators it was checked in controller already.
    private String firstName;

    @Column(nullable = false, length = 25)
    private String lastName;
    @Column(nullable = false, length = 30, unique = true)
    @Email(message = "Please enter provided e-mail")// it checks if email format is correct or not
    private String email;

    private String phoneNumber;

    private Integer grade;

    private LocalDateTime createDate = LocalDateTime.now();

}
