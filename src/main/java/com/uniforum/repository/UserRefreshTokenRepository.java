package com.uniforum.repository;
import com.uniforum.model.UserRefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRefreshTokenRepository extends MongoRepository<UserRefreshToken, String> {

    UserRefreshToken findByUserId(String userId);

}
