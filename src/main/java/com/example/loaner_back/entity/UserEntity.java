package com.example.loaner_back.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Size(min = 2, max = 30)
    String FirstName;
    String LastName;

    @NotBlank
    String email;

    @NotBlank
    String password;

    @NotNull
    int age;

    @OneToOne(mappedBy = "loanCreator")
    LoanEntity loanEntity;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<RoleEntity> roles;

    @ManyToMany(fetch = FetchType.EAGER)
    List<UserEntity> friendsIdList;

    public UserEntity() {
    }

    public UserEntity(@Size(min = 2, max = 30) String firstName, String lastName, @NotBlank String email, @NotBlank String password, @NotNull int age, Set<RoleEntity> roles) {
        FirstName = firstName;
        LastName = lastName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.roles = roles;
    }

    public void addRole(RoleEntity role) {
        roles.add(role);
    }
}
