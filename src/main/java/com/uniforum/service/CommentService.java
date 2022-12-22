package com.uniforum.service;
import com.uniforum.dto.CommentDto;
import com.uniforum.model.Comment;
import com.uniforum.request.CommentCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CommentService {

    Comment createComment(CommentCreateRequest commentCreateRequest);

    List<CommentDto> getAllUserComment(Optional<String> userId);

    List<CommentDto> getAllUniversityComment(Optional<String> universityId);

    Comment getCommentById(String commentId);

    List<CommentDto> getCommentAnswersByCommentId(Optional<String> connectedCommentId);

    String deleteCommentById(String commentId);

    CommentDto getOneCommentByIdWithLikes(String commentId);





    /* List<CommentDto> getAllUniversityComment(Optional<Long> universityId);*/
}
