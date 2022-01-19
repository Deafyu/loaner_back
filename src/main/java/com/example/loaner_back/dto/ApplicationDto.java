package com.example.loaner_back.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationDto {
    String name;
    String description;
    String loanName;
    String userEmail;
}
