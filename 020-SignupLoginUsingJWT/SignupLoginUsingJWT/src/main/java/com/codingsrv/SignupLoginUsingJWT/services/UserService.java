package com.codingsrv.SignupLoginUsingJWT.services;

import com.codingsrv.SignupLoginUsingJWT.dto.LoginDTO;
import com.codingsrv.SignupLoginUsingJWT.dto.SignUpDTO;
import com.codingsrv.SignupLoginUsingJWT.dto.UserDTO;
import com.codingsrv.SignupLoginUsingJWT.entities.User;
import com.codingsrv.SignupLoginUsingJWT.exception.ResourceNotFoundException;
import com.codingsrv.SignupLoginUsingJWT.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

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
                .orElseThrow(()-> new ResourceNotFoundException("User not found with email: "+username));
    }






}
