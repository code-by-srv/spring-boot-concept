package com.codingsrv.ApiCallWithRestClient.clients;

import com.codingsrv.ApiCallWithRestClient.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeClient {

    EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO);

    List<EmployeeDTO> getAllEmployees();

    EmployeeDTO getEmployeeById(Long employeeId);



}
