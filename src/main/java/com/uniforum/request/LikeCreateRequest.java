package com.uniforum.request;
import lombok.Data;
@Data
public class LikeCreateRequest {
    private String id;
    private String userId;
    private String commentId;
}
