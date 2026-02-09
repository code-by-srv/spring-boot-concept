package com.codingsrv.ApiCallWithRestClient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Integer id;
    private String name;
    private Integer age;
    private String email;
    private Boolean isActive;
    private String role;
}
