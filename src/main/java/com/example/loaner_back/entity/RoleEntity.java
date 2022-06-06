package com.example.loaner_back.entity;

import lombok.AccessLevel;

import lombok.Getter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class RoleEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    Long id;

    @NotBlank
    String name;

    @ManyToMany(fetch = FetchType.EAGER)
//    @ManyToMany
    List<UserEntity> users;
}
