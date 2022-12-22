package com.uniforum.request;
import lombok.Data;
@Data
public class UserRefreshRequest {

    private String userId;
    private String refreshToken;

}
