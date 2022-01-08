package com.example.loaner_back.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class UserEntity {
    @Id
    Long id;

    @NotBlank
    @Size(min = 6, max = 30)
    String FirstName;
    String LastName;

    @NotBlank
    String email;

    @NotBlank
    String password;

    @NotBlank
    int age;

    @OneToOne(mappedBy = "loanCreator")
    private LoanEntity user;

    @ManyToMany
    private Set<RoleEntity> roles;
}
