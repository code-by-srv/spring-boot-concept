package com.codingsrv.JWTAccessRefreshAuthentication.services;

import com.codingsrv.JWTAccessRefreshAuthentication.dto.LoginDTO;
import com.codingsrv.JWTAccessRefreshAuthentication.dto.LoginResponseDTO;
import com.codingsrv.JWTAccessRefreshAuthentication.dto.SignUpDTO;
import com.codingsrv.JWTAccessRefreshAuthentication.dto.UserDTO;
import com.codingsrv.JWTAccessRefreshAuthentication.entities.User;
import com.codingsrv.JWTAccessRefreshAuthentication.repositories.UserRepository;
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
    private final UserService userService;



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


    public LoginResponseDTO login(LoginDTO loginDTO) {// will return token
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword()));

        User user = (User) authentication.getPrincipal();
        String accessToken =  jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new LoginResponseDTO(user.getId(),accessToken,refreshToken);
    }


    public LoginResponseDTO refreshToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        User user = userService.getUserById(userId);
        String accessToken =  jwtService.generateAccessToken(user);

        return new LoginResponseDTO(user.getId(),accessToken,refreshToken);

    }
}
