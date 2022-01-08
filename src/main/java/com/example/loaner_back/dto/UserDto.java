package com.example.loaner_back.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {

    @NotBlank
    String firstName;

    @NotBlank
    String lastName;

    @NotBlank
    String password;
    String matchingPassword;

    @NotBlank
    String email;
}
