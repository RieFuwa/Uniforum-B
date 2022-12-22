package com.uniforum.dto;
import com.uniforum.model.User;
import lombok.Data;
@Data
public class UserDto {
 //DATA TRANSFER OBJECT KISMI
    private String id;
    private String userName;
    private String userMail;

    public UserDto(User entity){
        this.id=entity.getId();
        this.userName= entity.getUserName();
        this.userMail=entity.getUserMail();
    }
}
