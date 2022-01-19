package com.example.loaner_back.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

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

    @OneToOne(fetch = FetchType.EAGER)
    UserEntity applicationCreator;

    @OneToOne(fetch = FetchType.EAGER)
    UserEntity applicationReceiver;

    @Lob
    private byte[] fileData;
}
