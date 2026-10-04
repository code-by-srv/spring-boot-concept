package com.codingsrv.GranularAuthorization.dto;


import com.codingsrv.GranularAuthorization.entities.util.Permission;
import com.codingsrv.GranularAuthorization.entities.util.Role;
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