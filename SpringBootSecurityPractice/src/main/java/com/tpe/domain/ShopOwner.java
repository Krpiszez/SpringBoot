package com.tpe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "t_shop_owner")
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class ShopOwner {

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
    @NotBlank(message = "Last Name cannot be blank!")
    @NotNull(message = "Please enter a value for last name!")
    @Size(min = 2, max = 50, message = "The ${validatedValue} must be between {min} and {max} characters!")
    @Column(unique = true)
    private String shopName;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    public ShopOwner(String name, String lastName, String email, String phone, String shopName) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.shopName = shopName;
    }
}
