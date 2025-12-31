package com.codingsrv2.LearningRESTAPIs.dto;

import com.codingsrv2.LearningRESTAPIs.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class EmployeeDTO {

    private String name;
    private Integer age;
    private String email;
    private Boolean isActive;
    private Integer id;
    private String role;
    private LocalDate dateOfJoining;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Integer id, String name, Integer age, String email, Boolean isActive, String role, LocalDate dateOfJoining) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.isActive = isActive;
        this.role = role;
        this.dateOfJoining = dateOfJoining;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }
}

