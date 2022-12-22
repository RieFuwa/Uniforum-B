package com.uniforum.service.Impl;
import com.uniforum.dto.CommentDto;
import com.uniforum.dto.LikeDto;
import com.uniforum.exception.NotFoundException;
import com.uniforum.model.Comment;
import com.uniforum.model.University;
import com.uniforum.model.User;
import com.uniforum.repository.CommentRepository;
import com.uniforum.request.CommentCreateRequest;
import com.uniforum.service.CommentService;
import com.uniforum.service.LikeService;
import com.uniforum.service.UniversityService;
import com.uniforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UniversityService universityService;
    @Autowired
    private UserService userService;

    private LikeService likeService;

    @Autowired
    public void setLikeService(LikeService likeService) {
        this.likeService = likeService;
    }

    @Override
    public Comment createComment(CommentCreateRequest commentCreateRequest) {
        User user = userService.getUserById(commentCreateRequest.getUserId());
        University university = universityService.getUniversityById(commentCreateRequest.getUniversityId());

        if (user==null && university==null){
            return null;
        }
        Comment toCreate = new Comment();
        toCreate.setId(commentCreateRequest.getId());
        toCreate.setCommentText(commentCreateRequest.getCommentText());

        if(commentCreateRequest.getConnectedCommentId()!=null){
            toCreate.setConnectedComment(getCommentById(commentCreateRequest.getConnectedCommentId()));
        }

        toCreate.setUser(user);
        toCreate.setUniversity(university);
        toCreate.getUniversity().setId(commentCreateRequest.getUniversityId());
        toCreate.setCreateDate(new Date());
        return commentRepository.save(toCreate);
    }

    @Override
    public List<CommentDto> getAllUserComment(Optional<String>userId) {
        List<Comment> comments;
        if(userId.isPresent()){
            comments=commentRepository.findByUserId(userId.get());
        }else
            comments=commentRepository.findAll();
        return comments.stream().map(p -> {
            List<LikeDto> likes = likeService.getAllLikesWithParam(Optional.ofNullable(null), Optional.of(p.getId()));

            return new CommentDto(p, likes);}).collect(Collectors.toList());

}


    @Override
    public List<CommentDto> getAllUniversityComment(Optional<String> universityId) {
        List<Comment> comments;
        if(universityId.isPresent()){
            comments=commentRepository.findByUniversityId(universityId.get());
        }else
            comments=commentRepository.findAll();
        return comments.stream().map(p -> {
            List<LikeDto> likes = likeService.getAllLikesWithParam(Optional.ofNullable(null), Optional.of(p.getId()));
        return new CommentDto(p, likes);}).collect(Collectors.toList());

    }

    @Override
    public List<CommentDto> getCommentAnswersByCommentId(Optional<String> connectedCommentId) {
        List<Comment> comments;
        if(connectedCommentId.isPresent()){
            comments=commentRepository.findByConnectedCommentId(connectedCommentId.get());
        }else
            comments= commentRepository.findAll();
        return comments.stream().map(p -> {
            List<LikeDto> likes = likeService.getAllLikesWithParam(Optional.ofNullable(null), Optional.of(p.getId()));

            return new CommentDto(p, likes);}).collect(Collectors.toList());
    }

    @Override
    public String deleteCommentById(String commentId) {
        if(!commentRepository.existsById(commentId)){
            throw new NotFoundException(commentId);
        }
        commentRepository.deleteById(commentId);
        return "Comment with id " +commentId+ " has been deleted success.";
    }

    @Override
    public CommentDto getOneCommentByIdWithLikes(String commentId) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        List<LikeDto> likes = likeService.getAllLikesWithParam(Optional.ofNullable(null), Optional.of(commentId));
        return new CommentDto(comment, likes);
    }


    @Override
    public Comment getCommentById(String commentId) {
        return commentRepository.findById(commentId).orElseThrow(()->new NotFoundException(commentId));
    }


}
