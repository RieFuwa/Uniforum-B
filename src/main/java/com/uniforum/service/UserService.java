package com.uniforum.service;
import com.uniforum.dto.UserAuthDto;
import com.uniforum.model.User;
import com.uniforum.request.UserAuthRequest;
import com.uniforum.request.UserRefreshRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    User createUser(User newUser);

    List<User> getAllUser();

    User getUserById(String userId);

    User  getOneUserByUserMail(String userMail);

    User  saveOneUser(User newUser);

    String deleteUserById(String userId);

    User updateUserById(String userId, User newUser);

    ResponseEntity<UserAuthDto> registerUser(UserAuthRequest registerRequest);

    UserAuthDto loginUser(UserAuthRequest loginRequest);

    ResponseEntity<UserAuthDto> refreshUserToken(UserRefreshRequest refreshRequest);

}
