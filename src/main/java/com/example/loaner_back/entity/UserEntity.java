package com.example.loaner_back.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Set;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Size(min = 6, max = 30)
    String FirstName;
    String LastName;

    @NotBlank
    String email;

    @NotBlank
    String password;

    @NotNull
    int age;

    @OneToOne(mappedBy = "loanCreator")
    private LoanEntity user;

    @ManyToMany
    private Set<RoleEntity> roles;

    public void addRole(RoleEntity role){
        roles.add(role);
    }
}
