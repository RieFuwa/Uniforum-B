package com.uniforum.controller;
import com.uniforum.dto.LikeDto;
import com.uniforum.model.Like;
import com.uniforum.request.LikeCreateRequest;
import com.uniforum.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/like")
@CrossOrigin
public class LikeController {
    @Autowired
    private LikeService likeService;

    @GetMapping("/getAll")
    public List<LikeDto> getAllLikes(@RequestParam Optional<String> userId, @RequestParam Optional<String> commentId){
        return likeService.getAllLikesWithParam(userId,commentId);
    }

    @PostMapping("/add")
    public Like createOneLike(@RequestBody LikeCreateRequest likeCreateRequest){
        return  likeService.createOneLike(likeCreateRequest);
    }

    @DeleteMapping("/{likeId}")
    public String deleteOneLike(@PathVariable("likeId") String likeId){
        return likeService.deleteOneLike(likeId);
    }

    @GetMapping("/{likeId}")
    public  LikeDto getOneLikeById(@PathVariable String likeId){
        Like like = likeService.getOneLikeById(likeId);
        return new LikeDto(like);
    }



    @GetMapping("/getOneUserLikes{userId}")
    public List<LikeDto> getOneUserLikes(@RequestParam Optional<String> userId){
        return likeService.getOneUserLikes(userId);
    }

}
