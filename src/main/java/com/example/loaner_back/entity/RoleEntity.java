package com.example.loaner_back.entity;

import com.example.loaner_back.utils.UserType;
import lombok.AccessLevel;

import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class RoleEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    Long id;

    String name;

    @NotNull
    UserType userType;
}
