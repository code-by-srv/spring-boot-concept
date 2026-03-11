package com.codingsrv.AuthenticatingRequestsUsingJWT.dto;

import lombok.Data;

@Data
public class LoginDTO {

    private String email;
    private String password;

}

