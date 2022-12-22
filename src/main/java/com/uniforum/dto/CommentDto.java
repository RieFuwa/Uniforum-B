package com.uniforum.dto;
import com.uniforum.model.Comment;
import com.uniforum.model.User;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class CommentDto {
    private String id;
    private String universityId;
    private User user;
    private String connectedCommentId;

    private String commentText;
    private Date createDate;
    private List<LikeDto> commentLikes;


    public CommentDto(Comment entity,List<LikeDto> commentLikes){
        this.id=entity.getId();
        if(entity.getConnectedComment()!=null){
            this.connectedCommentId=entity.getConnectedComment().getId();
        }
        this.universityId=entity.getUniversity().getId();
        this.user=entity.getUser();
        this.commentText=entity.getCommentText();
        this.createDate=entity.getCreateDate();
        this.commentLikes = commentLikes;
    }

}
