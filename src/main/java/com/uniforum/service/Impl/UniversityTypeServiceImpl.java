package com.uniforum.service.Impl;
import com.uniforum.exception.NotFoundException;
import com.uniforum.model.UniversityType;
import com.uniforum.repository.UniversityTypeRepository;
import com.uniforum.service.UniversityTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class UniversityTypeServiceImpl implements UniversityTypeService {
    @Autowired
    private UniversityTypeRepository universityTypeRepository;

    @Override
    public UniversityType createUniversityType(UniversityType newUniversityType) {
        return universityTypeRepository.save(newUniversityType);
    }

    @Override
    public List<UniversityType> getAllUniversityType() {
       return universityTypeRepository.findAll();
    }

    @Override
    public UniversityType getUniversityTypeById(String universityTypeId) {
        return  universityTypeRepository.findById(universityTypeId).orElseThrow(()->new NotFoundException(universityTypeId));
    }

    @Override
    public String deleteUniversityType(String universityTypeId) {
        if(!universityTypeRepository.existsById(universityTypeId)){
            throw new NotFoundException(universityTypeId);
        }
        universityTypeRepository.deleteById(universityTypeId);
        return "UniversityType with id " +universityTypeId+ " has been deleted success.";
    }

    @Override
    public UniversityType updateUniversityTypeById(String universityTypeId, UniversityType newUniversityType) {
        Optional<UniversityType> universityType = universityTypeRepository.findById(universityTypeId);
        if(universityType.isPresent()){
            UniversityType toUpdate = universityType.get();
            toUpdate.setUniversityTypeName(toUpdate.getUniversityTypeName());
            return  universityTypeRepository.save(toUpdate);
        }else
            return null;
    }
}
