package com.uniforum.service.Impl;

import com.uniforum.exception.NotFoundException;
import com.uniforum.model.UserType;
import com.uniforum.repository.UserTypeRepository;
import com.uniforum.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserTypeServiceImpl implements UserTypeService {
    @Autowired
    private UserTypeRepository userTypeRepository;
    @Override
    public UserType createUserType(UserType newUserType) {
        return userTypeRepository.save(newUserType);
    }

    @Override
    public List<UserType> getAllUserType() {
        return userTypeRepository.findAll();
    }

    @Override
    public String deleteUserType(String userTypeId) {
        if(!userTypeRepository.existsById(userTypeId)){
            throw new NotFoundException(userTypeId);
        }
        userTypeRepository.deleteById(userTypeId);
        return "UserType with id " +userTypeId+ " has been deleted success.";
    }

    @Override
    public UserType getUserTypeById(String userTypeId) {
        return  userTypeRepository.findById(userTypeId).orElseThrow(()->new NotFoundException(userTypeId));    }

    @Override
    public UserType updateUserTypeById(String userTypeId, UserType newUserType) {
        Optional<UserType> userType = userTypeRepository.findById(userTypeId);
        if(userType.isPresent()){
            UserType toUpdate = userType.get();
            toUpdate.setUserTypeName(toUpdate.getUserTypeName());
            return  userTypeRepository.save(toUpdate);
        }else
            return null;
    }
}
