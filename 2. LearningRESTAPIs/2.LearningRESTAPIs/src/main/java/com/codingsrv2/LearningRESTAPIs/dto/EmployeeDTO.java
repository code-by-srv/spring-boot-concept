package com.codingsrv2.LearningRESTAPIs.dto;

public class EmployeeDTO {



    private String name;
    private Integer age;
    private String email;
    private Boolean isActive;
    private Integer id;


    public EmployeeDTO() {
    }

    public EmployeeDTO(String name, int id, int age, String email, Boolean isActive) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.email = email;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

