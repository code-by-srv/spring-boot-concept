package com.codingsrv.SpringBootGoogleOAuth2Authentication.controller;

import com.codingsrv.SpringBootGoogleOAuth2Authentication.dto.LoginDTO;
import com.codingsrv.SpringBootGoogleOAuth2Authentication.dto.LoginResponseDTO;
import com.codingsrv.SpringBootGoogleOAuth2Authentication.dto.SignUpDTO;
import com.codingsrv.SpringBootGoogleOAuth2Authentication.dto.UserDTO;
import com.codingsrv.SpringBootGoogleOAuth2Authentication.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @Value("${deploy.env}")
    private String deployEnv;  // injecting deploy environment in AuthController


    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO){
        UserDTO userDTO = authService.signUp(signUpDTO);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO, HttpServletResponse response){
        LoginResponseDTO loginResponseDTO = authService.login(loginDTO);  // save and then return access token
        Cookie cookie = new Cookie("refreshToken", loginResponseDTO.getRefreshToken()); // store refresh token in cookie
        cookie.setHttpOnly(true);    // setting the cookie as HttpOnly
        cookie.setSecure("production".equals(deployEnv)); // setSecure cookie only if deploy environment is production
        response.addCookie(cookie);  // in last add the cookie to response, which is of loginResponseDTO type.

        return ResponseEntity.ok(loginResponseDTO);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDTO> refresh(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            throw new AuthenticationServiceException("No cookies found");
        }
        String refreshToken = Arrays.stream(request.getCookies())
                                     .filter(cookie -> "refreshToken".equals(cookie.getName()))
                                     .findFirst()
                                     .map(cookie -> cookie.getValue())
                                     .orElseThrow(()-> new AuthenticationServiceException("Refresh token not found inside the cookie"));

        LoginResponseDTO loginResponseDTO = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(loginResponseDTO);
    }

}
