package com.codingsrv.ApiCallWithRestClient.controller;

import com.codingsrv.ApiCallWithRestClient.clients.EmployeeClient;
import com.codingsrv.ApiCallWithRestClient.dto.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/thirdpartyapi/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeClient employeeClient;


    @PostMapping
    public ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO savedEmployee = employeeClient.createNewEmployee(employeeDTO);
        return ResponseEntity.ok(savedEmployee);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeClient.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId){
        EmployeeDTO employee = employeeClient.getEmployeeById(employeeId);
        return ResponseEntity.ok(employee);
    }
}
