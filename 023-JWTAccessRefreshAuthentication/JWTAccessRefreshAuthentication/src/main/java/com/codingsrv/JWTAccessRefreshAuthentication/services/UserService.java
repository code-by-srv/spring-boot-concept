package com.codingsrv.JWTAccessRefreshAuthentication.services;

import com.codingsrv.JWTAccessRefreshAuthentication.entities.User;
import com.codingsrv.JWTAccessRefreshAuthentication.exception.ResourceNotFoundException;
import com.codingsrv.JWTAccessRefreshAuthentication.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new BadCredentialsException("User not found with email: " + username));
    }

    public User getUserById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id: "+userId));
    }


}
