package com.example.loaner_back.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class LoanDto {
    String name;
    String description;
    int interestRate;
    BigDecimal sum;
    String creatorLogin;
}
