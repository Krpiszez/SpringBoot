package com.tpe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_user")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 25, nullable = false)
    private String firstName;
    @Column(length = 25, nullable = false)
    private String lastName;
    @Column(length = 25, nullable = false, unique = true)
    private String username;
    @Column(length = 255, nullable = false)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_user_role"
            , joinColumns = {@JoinColumn(name = "user_id")}
            , inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<>();


}
