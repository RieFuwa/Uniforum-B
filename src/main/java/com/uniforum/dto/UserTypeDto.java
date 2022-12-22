package com.uniforum.dto;

import com.uniforum.model.UserType;
import lombok.Data;

@Data
public class UserTypeDto {

    private String id;
    private String userTypeName;

    public UserTypeDto(UserType entity){
        this.id=entity.getId();
        this.userTypeName= entity.getUserTypeName();
    }
}
