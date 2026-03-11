package com.codingsrv.SpringSecurityExceptionHandling.services;

import com.codingsrv.SpringSecurityExceptionHandling.dto.LoginDTO;
import com.codingsrv.SpringSecurityExceptionHandling.dto.SignUpDTO;
import com.codingsrv.SpringSecurityExceptionHandling.dto.UserDTO;
import com.codingsrv.SpringSecurityExceptionHandling.entities.User;
import com.codingsrv.SpringSecurityExceptionHandling.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthService {


    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;



    public UserDTO signUp(SignUpDTO signUpDTO) {
        Optional<User> user = userRepository.findByEmail(signUpDTO.getEmail());
        if (user.isPresent()){
            throw new BadCredentialsException("User with email already exist: "+signUpDTO.getEmail());
        }
        User createUser = modelMapper.map(signUpDTO, User.class);
        createUser.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
        User savedUser = userRepository.save(createUser);
        return modelMapper.map(savedUser, UserDTO.class);

    }


    public String login(LoginDTO loginDTO) {// will return token
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword()));

        User user = (User) authentication.getPrincipal();
        return jwtService.generateToken(user);

    }




}
