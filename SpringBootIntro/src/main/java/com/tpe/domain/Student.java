package com.tpe.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter // This getter and setter annotations from Lombok handle the getter-setter methods.
@AllArgsConstructor // This Annotation grants you a full variable constructor from this class.
//@RequiredArgsConstructor // if you dont want to use some variables in your class for your AllArgs constructor you should use this
//annotation and with it you need to make variables you want in your constructor - final!
@NoArgsConstructor // This Annotation grants you a non-variable constructor from this class.
@Entity
@Table(name = "t_student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) // doesn't create setter method for this variable.
    private Long id;
    @NotNull(message = "First Name can not be Null!")
    @NotBlank(message = "First Name can not be only \"Space Character\"")
    @Size(min = 4, max = 25, message = "First name \"${validatedValue}\" must be between {min} and {max} characters!")
    @Column(nullable = false, length = 25)//here it checks in DB but in validators it was checked in controller already.
    /*final*/ private String firstName;

    @Column(nullable = false, length = 25)
    /*final*/ private String lastName;
    @Column(nullable = false, length = 30, unique = true)
    @Email(message = "Please enter provided e-mail")// it checks if email format is correct or not
    /*final*/ private String email;

    /*final*/ private String phoneNumber;

    /*final*/ private Integer grade;
    @Setter(AccessLevel.NONE) // doesn't create setter method for this variable.
    private LocalDateTime createDate = LocalDateTime.now();

}
