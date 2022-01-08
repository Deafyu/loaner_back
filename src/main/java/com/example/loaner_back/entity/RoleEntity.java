package com.example.loaner_back.entity;

import com.example.loaner_back.utils.UserType;
import lombok.AccessLevel;

import lombok.Getter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class RoleEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    Long id;

    String name;

    @NotNull
    UserType userType;

    @ManyToMany
    List<UserEntity> users;
}
