package com.codingsrv.JWTCreationVerification.dto;

import lombok.Data;

@Data
public class JwtDTO {

    private Long id;
    private String email;
    private String password;

}
