package com.example.loaner_back.entity;

import com.example.loaner_back.utils.Type;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class ApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String description;

    boolean isAccepted;

    @NotNull
    Type type;

    @OneToOne
    UserEntity applicationCreator;

    @OneToOne
    UserEntity applicationReceiver;

    @Lob
    private byte[] fileData;
}
