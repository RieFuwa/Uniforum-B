package com.uniforum.dto;
import com.uniforum.model.UniversityType;
import lombok.Data;

@Data
public class UniversityTypeDto {
    private String id;
    private String universityTypeName;

    public UniversityTypeDto(UniversityType entity){
        this.id=entity.getId();
        this.universityTypeName= entity.getUniversityTypeName();
    }
}
