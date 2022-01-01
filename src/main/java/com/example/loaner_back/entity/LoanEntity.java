package com.example.loaner_back.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class LoanEntity {
    @Id
    Long id;

    @NotBlank
    String name;
    String description;

    @NotBlank
    @Min(0)
    @Max(30)
    int interestRate;

    @NotBlank
    BigDecimal sum;

    @OneToOne
    UserEntity loanCreator;
}
