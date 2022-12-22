package com.uniforum.service.Impl;
import com.uniforum.exception.NotFoundException;
import com.uniforum.model.University;
import com.uniforum.model.UniversityType;
import com.uniforum.repository.UniversityRepository;
import com.uniforum.request.UniversityCreateRequest;
import com.uniforum.request.UniversityUpdateRequest;
import com.uniforum.service.UniversityService;
import com.uniforum.service.UniversityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UniversityServiceImpl implements UniversityService {
    @Autowired
    private UniversityRepository universityRepository;
    @Autowired
    private UniversityTypeService universityTypeService;

    @Override
    public University createUniversity(UniversityCreateRequest universityCreateRequest) {
        UniversityType universityType = universityTypeService.getUniversityTypeById(universityCreateRequest.getUniversityTypeId());
        if(universityType==null)
            return null;
        University toCreate= new University();
        toCreate.setId(universityCreateRequest.getId());
        toCreate.setUniversityName(universityCreateRequest.getUniversityName());
        toCreate.setUniversityPhotos(universityCreateRequest.getUniversityPhotos());
        toCreate.setUniversityType(universityType);
        return universityRepository.save(toCreate);
    }

    @Override
    public List<University> getAllUniversity() {
        return universityRepository.findAll();
    }

    @Override
    public University getUniversityById(String universityId) {
        return  universityRepository.findById(universityId).orElseThrow(()->new NotFoundException(universityId));
    }

    @Override
    public String deleteUniversityById(String universityId) {
        if(!universityRepository.existsById(universityId)){
            throw new NotFoundException(universityId);
        }
        universityRepository.deleteById(universityId);
        return "University with id " +universityId+ " has been deleted success.";
    }

    @Override
    public University updateUniversityById(String universityId, UniversityUpdateRequest updateUniversity) {
        Optional<University> university = universityRepository.findById(universityId);
        if(university.isPresent()){
            University toUpdate = university.get();
            toUpdate.setUniversityName(updateUniversity.getUniversityName());
            toUpdate.setUniversityPhotos(updateUniversity.getUniversityPhotos());
            universityRepository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }


}
