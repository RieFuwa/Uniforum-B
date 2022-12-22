package com.uniforum.repository;

import com.uniforum.model.UserType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends MongoRepository<UserType,String> {
}
