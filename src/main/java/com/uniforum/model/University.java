package com.uniforum.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
@Entity
@Data
@Table(name="university")
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "universityType_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private UniversityType universityType;

    private String universityName;
    private String universityPhotos;


}
