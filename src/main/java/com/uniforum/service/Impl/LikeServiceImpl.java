package com.uniforum.service.Impl;

import com.uniforum.dto.LikeDto;
import com.uniforum.exception.NotFoundException;
import com.uniforum.model.Comment;
import com.uniforum.model.Like;
import com.uniforum.model.User;
import com.uniforum.repository.LikeRepository;
import com.uniforum.request.LikeCreateRequest;
import com.uniforum.service.CommentService;
import com.uniforum.service.LikeService;
import com.uniforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    @Override
    public List<LikeDto> getAllLikesWithParam(Optional<String> userId, Optional<String> commentId) {
        List<Like> list;
        if(userId.isPresent() && commentId.isPresent()){
            list = likeRepository.findByUserIdAndCommentId(userId.get(), commentId.get());
        } else if (userId.isPresent()) {
            list = likeRepository.findByUserId(userId.get());
        } else if (commentId.isPresent()) {
            list = likeRepository.findByCommentId(commentId.get());
        }else
            list = likeRepository.findAll();
        return list.stream().map(like-> new LikeDto(like)).collect(Collectors.toList());
    }

    @Override
    public Like createOneLike(LikeCreateRequest likeCreateRequest) {
        User user = userService.getUserById(likeCreateRequest.getUserId());
        Comment comment = commentService.getCommentById(likeCreateRequest.getCommentId());
        if(user !=null && comment != null){
            Like toCreate = new Like();
            toCreate.setId(likeCreateRequest.getId());
            toCreate.setUser(user);
            toCreate.setComment(comment);
            return likeRepository.save(toCreate);
        }else
            return null;
    }

    @Override
    public String deleteOneLike(String likeId) {
        if(!likeRepository.existsById(likeId)){
            throw new NotFoundException(likeId);
        }
        likeRepository.deleteById(likeId);
        return "Like with id " +likeId+ " has been deleted success.";
    }

    @Override
    public Like getOneLikeById(String likeId) {
        return  likeRepository.findById(likeId).orElseThrow(()->new NotFoundException(likeId));
    }



    @Override
    public List<LikeDto> getOneUserLikes(Optional<String> userId) {
        List<Like> likes;
        if(userId.isPresent()){
            likes=likeRepository.findByUserId(userId.get());
        }else
            likes = likeRepository.findAll();

        return likes.stream().map(p-> new LikeDto(p)).collect(Collectors.toList());
    }

}
