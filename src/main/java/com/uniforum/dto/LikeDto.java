package com.uniforum.dto;
import com.uniforum.model.Like;
import lombok.Data;

@Data
public class LikeDto {
    private String id ;
    private  String userId;
    private  String commentId;

    public LikeDto(Like entity){
        this.id=entity.getId();
        this.userId=entity.getUser().getId();
        this.commentId=entity.getComment().getId();
    }
}
