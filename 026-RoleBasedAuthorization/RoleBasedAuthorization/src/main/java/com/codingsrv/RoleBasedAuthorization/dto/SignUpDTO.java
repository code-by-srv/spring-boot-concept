package com.codingsrv.RoleBasedAuthorization.dto;

import com.codingsrv.RoleBasedAuthorization.entities.util.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignUpDTO {

    private String name;
    private String email;
    private String password;
    private Set<Role> roles;

}
//used for signup