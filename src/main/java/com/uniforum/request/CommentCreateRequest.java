package com.uniforum.request;
import lombok.Data;
@Data
public class CommentCreateRequest {
    private String id;
    private String universityId;
    private String userId;
    private String connectedCommentId;
    private String commentText;
}
