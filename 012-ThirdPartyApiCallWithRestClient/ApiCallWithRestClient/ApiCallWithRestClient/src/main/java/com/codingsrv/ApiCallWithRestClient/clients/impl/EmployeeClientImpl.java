package com.codingsrv.ApiCallWithRestClient.clients.impl;

import com.codingsrv.ApiCallWithRestClient.advice.ApiResponse;
import com.codingsrv.ApiCallWithRestClient.clients.EmployeeClient;
import com.codingsrv.ApiCallWithRestClient.dto.EmployeeDTO;
import com.codingsrv.ApiCallWithRestClient.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;


    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        try {
            ApiResponse<EmployeeDTO> employeeDTOApiResponse = restClient.post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeDTOApiResponse.getData();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
       try {
           ApiResponse<List<EmployeeDTO>> employeeDTOList = restClient.get()
                   .uri("employees")
                   .retrieve()
                   .body(new ParameterizedTypeReference<>() {
                   });
           return employeeDTOList.getData();
       }catch (Exception e){
            throw new RuntimeException(e);
       }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        try {
            ApiResponse<EmployeeDTO> employeeResponse = restClient.get()
                    .uri("employees/{employeeId}", employeeId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, ((request, response) -> {
                        System.out.println(new String(response.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Employee not found with ID: "+employeeId);
                    }))
                    .body(new ParameterizedTypeReference<>() {
                    });
            return employeeResponse.getData();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
