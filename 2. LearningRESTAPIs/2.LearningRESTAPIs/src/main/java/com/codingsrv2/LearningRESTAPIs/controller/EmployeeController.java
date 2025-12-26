package com.codingsrv2.LearningRESTAPIs.controller;

import com.codingsrv2.LearningRESTAPIs.dto.EmployeeDTO;
import com.codingsrv2.LearningRESTAPIs.entity.EmployeeEntity;
import com.codingsrv2.LearningRESTAPIs.repository.EmployeeRepository;
import com.codingsrv2.LearningRESTAPIs.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/srv")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable int employeeId){
        return employeeService.getEmployeeById(employeeId);
    }


    @GetMapping
    public List<EmployeeDTO> getEmployee(@RequestParam(required = false) Integer age, @RequestParam(required = false)
    Integer id){
        return employeeService.getEmployee();
    }



    @PostMapping
    public EmployeeDTO createNewEmployee(@RequestBody EmployeeDTO inputEmployee){
        return employeeService.createNewEmployee(inputEmployee);
    }
}


