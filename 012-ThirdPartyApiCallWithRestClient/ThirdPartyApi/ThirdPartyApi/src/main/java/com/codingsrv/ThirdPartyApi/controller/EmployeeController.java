package com.codingsrv.ThirdPartyApi.controller;

import com.codingsrv.ThirdPartyApi.dto.EmployeeDTO;
import com.codingsrv.ThirdPartyApi.exception.ResourceNotFoundException;
import com.codingsrv.ThirdPartyApi.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;


    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Integer employeeId) {

        EmployeeDTO employee = employeeService
                .getEmployeeById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id : " + employeeId));

        return ResponseEntity.ok(employee);
    }


    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {

        return ResponseEntity.ok(employeeService.getEmployee());
    }


    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {

        EmployeeDTO saved = employeeService.createNewEmployee(employeeDTO);

        return ResponseEntity.ok(saved);
    }


    @PutMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployee(
            @PathVariable Integer employeeId,
            @RequestBody EmployeeDTO employeeDTO) {

        return ResponseEntity.ok(
                employeeService.updateEmployeeById(employeeId, employeeDTO)
        );
    }



    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable Integer employeeId) {

        return ResponseEntity.ok(
                employeeService.deleteEmployeeById(employeeId)
        );
    }
}
