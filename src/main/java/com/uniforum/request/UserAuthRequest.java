package com.uniforum.request;

import lombok.Data;

@Data
public class UserAuthRequest {
    private String userName;
    private String userPassword;
    private String userMail;
    private String userTypeId;
}
