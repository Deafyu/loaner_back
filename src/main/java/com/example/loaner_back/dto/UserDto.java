package com.example.loaner_back.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    @NotNull
    int age;

    @NotBlank
    String password;
    String matchingPassword;

    @NotBlank
    String email;
}
