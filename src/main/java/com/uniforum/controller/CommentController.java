package com.uniforum.controller;

import com.uniforum.dto.CommentDto;
import com.uniforum.model.Comment;
import com.uniforum.request.CommentCreateRequest;
import com.uniforum.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
@CrossOrigin
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public Comment createComment(@RequestBody CommentCreateRequest commentCreateRequest){
        return commentService.createComment(commentCreateRequest);
    }

    @GetMapping("/getAllUserComment{userId}")
    public List<CommentDto> getAllUserComment(@RequestParam Optional<String> userId){
        return commentService.getAllUserComment(userId);
    }

    @GetMapping("/getAllUniversityComment{universityId}")
    public List<CommentDto> getAllUniversityComment(@RequestParam Optional<String> universityId){
        return commentService.getAllUniversityComment(universityId);
    }

    @GetMapping("/{commentId}")
    public CommentDto getCommentById(@PathVariable("commentId")String commentId){
        return commentService.getOneCommentByIdWithLikes(commentId);

    }

    @GetMapping("/commentAnswers{connectedCommentId}")
    public List<CommentDto> getCommentAnswersByCommentId(@RequestParam Optional<String> connectedCommentId){
        return commentService.getCommentAnswersByCommentId(connectedCommentId);
    }

    @DeleteMapping("/{commentId}")
    public String deleteCommentById(@PathVariable("commentId") String commentId){
        return commentService.deleteCommentById(commentId);
    }




}
