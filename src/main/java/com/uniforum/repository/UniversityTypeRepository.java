package com.uniforum.repository;
import com.uniforum.model.UniversityType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UniversityTypeRepository extends MongoRepository<UniversityType,String> {
}
