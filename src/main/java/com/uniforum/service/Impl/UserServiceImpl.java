package com.uniforum.service.Impl;
import com.uniforum.dto.UserAuthDto;
import com.uniforum.exception.NotFoundException;
import com.uniforum.model.User;
import com.uniforum.model.UserRefreshToken;
import com.uniforum.model.UserType;
import com.uniforum.repository.UserRepository;
import com.uniforum.request.UserAuthRequest;
import com.uniforum.request.UserRefreshRequest;
import com.uniforum.securityUser.JwtTokenProvider;
import com.uniforum.service.UserService;
import com.uniforum.service.UserTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRefreshTokenServiceImpl refreshTokenService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTypeService userTypeService;

    @Override
    public User createUser(User newUser) {
        return  userRepository.save(newUser);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(()->new NotFoundException(userId));
    }
    public User getOneUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User getOneUserByUserMail(String userMail) {
        return userRepository.findByUserMail(userMail);
    }

    public User saveOneUser(User newUser) {
        return userRepository.save(newUser);
    }
    @Override
    public String deleteUserById(String userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException(userId);
        }
        userRepository.deleteById(userId);
        return "User with id " +userId+ " has been deleted success.";
    }

    @Override
    public User updateUserById(String userId, User newUser) {
        Optional<User> user =userRepository.findById(userId);
        if(user.isPresent()){
            User foundUser = user.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setUserMail(newUser.getUserMail());
            foundUser.setUserPassword(newUser.getUserPassword());
            return userRepository.save(foundUser);
        }else
            return null;
    }

    @Override
    public ResponseEntity<UserAuthDto> registerUser(UserAuthRequest registerRequest) {
        UserAuthDto authResponse = new UserAuthDto();
        UserType userType = userTypeService.getUserTypeById(registerRequest.getUserTypeId());
        if(userRepository.findByUserMail(registerRequest.getUserMail()) != null ) {
            authResponse.setMessage("Mail already in use.");
            return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
        }
        if(userType==null)
            return null;
        User user = new User();
        user.setUserName(registerRequest.getUserName());
        user.setUserMail(registerRequest.getUserMail());
        user.setUserPassword(passwordEncoder.encode(registerRequest.getUserPassword()));
        user.setUserType(userType);
        userRepository.save(user);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(registerRequest.getUserMail(), registerRequest.getUserPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        authResponse.setMessage("User successfully registered.");
        authResponse.setAccessToken("Bearer " + jwtToken);
        authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));
        authResponse.setUserId(user.getId());
        authResponse.setUserName(user.getUserName());
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @Override
    public UserAuthDto loginUser(UserAuthRequest loginRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getUserMail(), loginRequest.getUserPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);
        User user = userRepository.findByUserMail(loginRequest.getUserMail());
        UserAuthDto authResponse = new UserAuthDto();
        authResponse.setMessage("User successfully login.");
        authResponse.setAccessToken("Bearer " + jwtToken);
        authResponse.setRefreshToken(refreshTokenService.createRefreshToken(user));
        authResponse.setUserId(user.getId());
        authResponse.setUserName(user.getUserName());
        return authResponse;
    }

    @Override
    public ResponseEntity<UserAuthDto> refreshUserToken(UserRefreshRequest refreshRequest) {
        UserAuthDto response = new UserAuthDto();
        UserRefreshToken token = refreshTokenService.getByUser(refreshRequest.getUserId());
        if(token.getToken().equals(refreshRequest.getRefreshToken()) &&
                !refreshTokenService.isRefreshExpired(token)) {

            User user = token.getUser();
            String jwtToken = jwtTokenProvider.generateJwtTokenByUserId(user.getId());
            response.setMessage("token successfully refreshed.");
            response.setAccessToken("Bearer " + jwtToken);
            response.setUserId(user.getId());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setMessage("refresh token is not valid.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
}
