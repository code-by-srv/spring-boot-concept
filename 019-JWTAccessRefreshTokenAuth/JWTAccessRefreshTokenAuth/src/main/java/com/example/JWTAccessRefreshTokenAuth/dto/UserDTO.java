package com.example.JWTAccessRefreshTokenAuth.dto;

import lombok.Data;

@Data
public class UserDTO {

    private  Long id;
    private String name;
    private String email;

}
// used for response