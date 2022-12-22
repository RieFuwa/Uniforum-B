package com.uniforum.service;

import com.uniforum.model.UserType;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public interface UserTypeService {
    UserType createUserType(UserType newUserType);

    List<UserType> getAllUserType();

    String deleteUserType(String userTypeId);

    UserType getUserTypeById(String userTypeId);

    UserType updateUserTypeById(String userTypeId, UserType newUserType);
}
