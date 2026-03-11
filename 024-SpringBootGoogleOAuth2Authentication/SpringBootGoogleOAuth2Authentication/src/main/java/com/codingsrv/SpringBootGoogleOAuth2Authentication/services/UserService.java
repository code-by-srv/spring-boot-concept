package com.codingsrv.SpringBootGoogleOAuth2Authentication.services;

import com.codingsrv.SpringBootGoogleOAuth2Authentication.entities.User;
import com.codingsrv.SpringBootGoogleOAuth2Authentication.exception.ResourceNotFoundException;
import com.codingsrv.SpringBootGoogleOAuth2Authentication.repositories.UserRepository;
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

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }


    public User save(User newUser) {
        return userRepository.save(newUser);
    }
}
