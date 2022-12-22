package com.uniforum.repository;
import com.uniforum.model.University;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UniversityRepository extends MongoRepository<University,String> {
}
