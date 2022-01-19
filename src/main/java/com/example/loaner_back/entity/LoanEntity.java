package com.example.loaner_back.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class, property="id")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class LoanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique=true)
    @NotBlank
    String name;

    @NotBlank
    String description;

    @NotNull
    @Min(0)
    @Max(30)
    int interestRate;

    @NotNull
    BigDecimal sum;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    UserEntity loanCreator;

    @ManyToMany(cascade = CascadeType.ALL)
    List<UserEntity> loanReceivers;
}
