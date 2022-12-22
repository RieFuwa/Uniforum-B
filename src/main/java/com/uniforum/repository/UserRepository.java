package com.uniforum.repository;
import com.uniforum.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    User findByUserName(String userName);
    User findByUserMail(String userMail);
}
