package com.uniforum.dto;
import lombok.Data;

@Data
public class UserAuthDto {
    private String message;
    private String userId;
    private String userName;
    private String accessToken;
    private String refreshToken;
}
