package com.codingsrv2.LearningRESTAPIs.controller;

import com.codingsrv2.LearningRESTAPIs.dto.EmployeeDTO;
import com.codingsrv2.LearningRESTAPIs.entity.EmployeeEntity;
import com.codingsrv2.LearningRESTAPIs.exceptions.ResourceNotFoundException;
import com.codingsrv2.LearningRESTAPIs.repository.EmployeeRepository;
import com.codingsrv2.LearningRESTAPIs.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/srv")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable int employeeId){
        Optional<EmployeeDTO> employeeDTO = Optional.ofNullable(employeeService.getEmployeeById(employeeId));

        return employeeDTO
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Resource not found"));

    }


    @GetMapping
    public List<EmployeeDTO> getEmployee(@RequestParam(required = false) Integer age, @RequestParam(required = false)
    Integer id){
        return employeeService.getEmployee();
    }


    @PostMapping
    public EmployeeDTO createNewEmployee(@Valid @RequestBody EmployeeDTO inputEmployee){
        return employeeService.createNewEmployee(inputEmployee);
    }

    @PutMapping("/{employeeId}")
    public EmployeeDTO updateEmployeeById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Integer employeeId){
        return employeeService.updateEmployeeById(employeeId,employeeDTO);
    }

    @DeleteMapping("/{employeeId}")
    public boolean deleteEmployeeById(@PathVariable Integer employeeId){
        return employeeService.deleteEmployeeById(employeeId);
    }

//    @PatchMapping("/{employeeId}")
//    public EmployeeDTO updatePartialByEmployeeId(@PathVariable Integer employeeId,
//                                                 @RequestBody Map<String, Object> updates){
//        return employeeService.updatePartialByEmployeeId(employeeId,updates);
//    }

}


