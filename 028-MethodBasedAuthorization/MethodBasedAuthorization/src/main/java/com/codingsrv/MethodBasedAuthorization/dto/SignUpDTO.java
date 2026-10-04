package com.codingsrv.MethodBasedAuthorization.dto;


import com.codingsrv.MethodBasedAuthorization.entities.util.Permission;
import com.codingsrv.MethodBasedAuthorization.entities.util.Role;
import lombok.Data;

import java.util.Set;

@Data
public class SignUpDTO {

    private String name;
    private String email;
    private String password;
    private Set<Role> roles;
    private Set<Permission> permissions;

}
//used for signup