package com.tpe.dto.response;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CustomerResponse {

    private String name;

    private String lastName;

    private String email;

    private String phone;

    private String userName;


}
