package com.uniforum.service.Impl;
import com.uniforum.model.User;
import com.uniforum.repository.UserRepository;
import com.uniforum.securityUser.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String userMail) throws UsernameNotFoundException {
        User user =userRepo.findByUserMail(userMail);
        return JwtUserDetails.create(user);
    }

    public UserDetails loadUserById(String userId) {
        User user = userRepo
                .findById(userId).get();
        return JwtUserDetails.create(user);
    }

}
