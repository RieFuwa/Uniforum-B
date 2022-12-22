package com.uniforum.service;
import com.uniforum.model.University;
import com.uniforum.request.UniversityCreateRequest;
import com.uniforum.request.UniversityUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UniversityService {

    University createUniversity(UniversityCreateRequest universityCreateRequest);

    List<University> getAllUniversity();

    University getUniversityById(String universityId);

    String deleteUniversityById(String universityId);

    University updateUniversityById(String universityId, UniversityUpdateRequest updateUniversity);
}
