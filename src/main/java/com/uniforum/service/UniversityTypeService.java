package com.uniforum.service;
import com.uniforum.model.UniversityType;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public interface UniversityTypeService {
    UniversityType createUniversityType(UniversityType newUniversityType);

    List<UniversityType> getAllUniversityType();

    UniversityType getUniversityTypeById(String universityTypeId);

    String deleteUniversityType(String universityTypeId);

    UniversityType updateUniversityTypeById(String universityTypeId, UniversityType newUniversityType);
}
