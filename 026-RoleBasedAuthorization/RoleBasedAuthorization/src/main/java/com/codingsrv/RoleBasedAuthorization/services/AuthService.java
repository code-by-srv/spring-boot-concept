package com.codingsrv.RoleBasedAuthorization.services;

import com.codingsrv.RoleBasedAuthorization.dto.LoginDTO;
import com.codingsrv.RoleBasedAuthorization.dto.LoginResponseDTO;
import com.codingsrv.RoleBasedAuthorization.dto.SignUpDTO;
import com.codingsrv.RoleBasedAuthorization.dto.UserDTO;
import com.codingsrv.RoleBasedAuthorization.entities.User;
import com.codingsrv.RoleBasedAuthorization.repositories.UserRepository;
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
    private final SessionService sessionService;



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

    // it will return token
    public LoginResponseDTO login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),loginDTO.getPassword()));

        User user = (User) authentication.getPrincipal();
        String accessToken =  jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);
        sessionService.generateNewSession(user, refreshToken);

        return new LoginResponseDTO(user.getId(),accessToken,refreshToken);
    }


    public LoginResponseDTO refreshToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        sessionService.validateSession(refreshToken);
        User user = userService.getUserById(userId);

        String accessToken =  jwtService.generateAccessToken(user);

        return new LoginResponseDTO(user.getId(),accessToken,refreshToken);

    }
}
