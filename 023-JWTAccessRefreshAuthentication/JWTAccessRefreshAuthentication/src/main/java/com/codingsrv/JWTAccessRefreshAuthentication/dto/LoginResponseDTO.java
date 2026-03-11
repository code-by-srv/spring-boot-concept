package com.codingsrv.JWTAccessRefreshAuthentication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponseDTO {

    private Long id;
    private String accessToken;
    private String refreshToken;


}
