package com.example.loaner_back.dto.Application;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApplicationAnswerDto {
    boolean accepted;
    long applicationId;
    String message;
}
