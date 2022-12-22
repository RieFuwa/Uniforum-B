package com.uniforum.dto;
import com.uniforum.model.University;
import com.uniforum.model.UniversityType;
import lombok.Data;
@Data
public class UniversityDto {

    private String id;
    private UniversityType universityType;
    private  String universityName;
    private  String universityPhotos;




    public UniversityDto(University entity){
        this.id=entity.getId();
        this.universityType=entity.getUniversityType();
        this.universityName=entity.getUniversityName();
        this.universityPhotos=entity.getUniversityPhotos();
    }
}
