package com.uniforum.model;
import lombok.Data;
import javax.persistence.*;
@Entity
@Data
@Table(name="UniversityType")
public class UniversityType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;

    private String universityTypeName;

}
