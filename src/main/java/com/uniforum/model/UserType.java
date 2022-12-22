package com.uniforum.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="userType")
public class UserType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;

    private String userTypeName;

}

