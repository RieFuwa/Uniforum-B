package com.uniforum.repository;
import com.uniforum.model.Like;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LikeRepository extends MongoRepository<Like,String> {

    List<Like> findByUserIdAndCommentId(String userId, String commentId);

    List<Like> findByUserId(String userId);

    List<Like> findByCommentId(String commentId);


}
