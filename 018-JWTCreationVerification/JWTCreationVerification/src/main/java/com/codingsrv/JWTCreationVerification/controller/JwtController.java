package com.codingsrv.JWTCreationVerification.controller;

import com.codingsrv.JWTCreationVerification.entities.User;
import com.codingsrv.JWTCreationVerification.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class JwtController {

    private final JwtService jwtService;

    // 🔹 Generate Token
    @PostMapping("/generate")
    public String generateToken(@RequestBody User user) {

        if (user.getId() == null) {
            throw new IllegalArgumentException("Please provide user ID in request body");
        }

        return jwtService.generateToken(user);
    }

    // 🔹 Validate Token
    @PostMapping("/validate")
    public String validateToken(@RequestHeader("Authorization") String authHeader, @RequestBody User user) {

        if (user.getId() == null) {
            throw new IllegalArgumentException("Please provide user ID in request body");
        }

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid Authorization header");
        }
        // to extract the actual JWT from the Authorization: Bearer header, which we are sending through Postman.
        String token = authHeader.substring(7).trim();

        boolean isValid = jwtService.validateToken(token, user);

        return isValid ? "Token is valid ✅" : "Token is invalid ❌";
    }



    // 🔹 Extract User ID
    @GetMapping("/userId")
    public Long getUserId(@RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7).trim();
        return jwtService.getUserIdFromToken(token);
    }

}
