package com.codingsrv.SpringBootGoogleOAuth2Authentication.dto;

import lombok.Data;

@Data
public class SignUpDTO {

    private String name;
    private String email;
    private String password;

}
//used for signup