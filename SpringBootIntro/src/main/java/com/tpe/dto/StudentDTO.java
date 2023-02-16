package com.tpe.dto;

import com.tpe.domain.Student;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class StudentDTO {

    private Long id;
    @NotNull(message = "First Name can not be Null!")
    @NotBlank(message = "First Name can not be only \"Space Character\"")
    @Size(min = 4, max = 25, message = "First name \"${validatedValue}\" must be between {min} and {max} characters!")
    private String name;

    private String lastName;
    @Email(message = "Please enter provided e-mail")// it checks if email format is correct or not
    private String email;

    private String phoneNumber;

    private Integer grade;

    private LocalDateTime createDate = LocalDateTime.now();

    public StudentDTO(Student student){
        this.id = student.getId();
        this.name = student.getFirstName();
        this.lastName = student.getLastName();
        this.grade = student.getGrade();
        this.createDate  =student.getCreateDate();
        this.email  = student.getEmail();
        this.phoneNumber = student.getPhoneNumber();
    }

}