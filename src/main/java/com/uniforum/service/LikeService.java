package com.uniforum.service;

import com.uniforum.dto.LikeDto;
import com.uniforum.model.Like;
import com.uniforum.request.LikeCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LikeService {

    List<LikeDto> getAllLikesWithParam(Optional<String> userId, Optional<String> commentId);

    Like createOneLike(LikeCreateRequest likeCreateRequest);

    String deleteOneLike(String likeId);

    Like getOneLikeById(String likeId);


    List<LikeDto> getOneUserLikes(Optional<String> userId);

}
