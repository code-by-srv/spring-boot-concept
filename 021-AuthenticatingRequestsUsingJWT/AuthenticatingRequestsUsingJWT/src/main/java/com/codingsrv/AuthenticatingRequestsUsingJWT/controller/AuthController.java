package com.codingsrv.AuthenticatingRequestsUsingJWT.controller;

import com.codingsrv.AuthenticatingRequestsUsingJWT.dto.LoginDTO;
import com.codingsrv.AuthenticatingRequestsUsingJWT.dto.SignUpDTO;
import com.codingsrv.AuthenticatingRequestsUsingJWT.dto.UserDTO;
import com.codingsrv.AuthenticatingRequestsUsingJWT.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO){
        UserDTO userDTO = authService.signUp(signUpDTO);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO, HttpServletResponse response){
        String token = authService.login(loginDTO);
        Cookie cookie = new Cookie("token",token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(token);
    }

}
