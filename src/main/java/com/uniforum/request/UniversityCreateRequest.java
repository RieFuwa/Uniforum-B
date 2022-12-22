package com.uniforum.request;
import lombok.Data;
@Data
public class UniversityCreateRequest {

    private String id;
    private String universityTypeId;
    private String universityName;
    private String universityPhotos;

}
