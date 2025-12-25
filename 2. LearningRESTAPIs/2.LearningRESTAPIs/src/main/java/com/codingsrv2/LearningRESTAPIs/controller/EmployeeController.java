package com.codingsrv2.LearningRESTAPIs.controller;

import com.codingsrv2.LearningRESTAPIs.dto.EmployeeDTO;
import com.codingsrv2.LearningRESTAPIs.entity.EmployeeEntity;
import com.codingsrv2.LearningRESTAPIs.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/srv")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    @GetMapping("/user/{employeeId}")
    public EmployeeEntity getEmployeeById2(@PathVariable int employeeId){
        return employeeRepository.findById(employeeId).orElse(null);
    }


    @GetMapping("/user1")
    public List<EmployeeEntity> getEmployee(@RequestParam(required = false) int age, @RequestParam(required = false)
    int id){
        return employeeRepository.findAll();
    }

    @GetMapping
    public List<EmployeeEntity> getAllEmployee(){
        return employeeRepository.findAll();
    }



    @PostMapping
    public EmployeeEntity createNewEmployee(@RequestBody EmployeeEntity inputEmployee){
        return employeeRepository.save(inputEmployee);
    }
}


