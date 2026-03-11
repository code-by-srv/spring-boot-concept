package com.example.JWTAccessRefreshTokenAuth.dto;

import lombok.Data;

@Data
public class SignUpDTO {

    private String name;
    private String email;
    private String password;

}
//used for signup