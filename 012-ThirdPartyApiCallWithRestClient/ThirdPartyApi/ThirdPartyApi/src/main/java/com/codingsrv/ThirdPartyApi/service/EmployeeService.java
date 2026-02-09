package com.codingsrv.ThirdPartyApi.service;

import com.codingsrv.ThirdPartyApi.dto.EmployeeDTO;
import com.codingsrv.ThirdPartyApi.entity.EmployeeEntity;
import com.codingsrv.ThirdPartyApi.exception.ResourceNotFoundException;
import com.codingsrv.ThirdPartyApi.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;


    public Optional<EmployeeDTO> getEmployeeById(int employeeId) {

        return employeeRepository.findById(employeeId)
                .map(emp -> modelMapper.map(emp, EmployeeDTO.class));
    }


    public List<EmployeeDTO> getEmployee() {

        return employeeRepository.findAll()
                .stream()
                .map(emp -> modelMapper.map(emp, EmployeeDTO.class))
                .collect(Collectors.toList());
    }


    public EmployeeDTO createNewEmployee(EmployeeDTO dto) {

        EmployeeEntity entity = modelMapper.map(dto, EmployeeEntity.class);

        EmployeeEntity saved = employeeRepository.save(entity);

        return modelMapper.map(saved, EmployeeDTO.class);
    }



    public EmployeeDTO updateEmployeeById(Integer employeeId, EmployeeDTO dto) {

        EmployeeEntity existing = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id : " + employeeId));

        modelMapper.map(dto, existing);

        EmployeeEntity updated = employeeRepository.save(existing);

        return modelMapper.map(updated, EmployeeDTO.class);
    }


    public boolean deleteEmployeeById(Integer employeeId) {

        if (!employeeRepository.existsById(employeeId)) {
            throw new ResourceNotFoundException(
                    "Employee not found with id : " + employeeId);
        }

        employeeRepository.deleteById(employeeId);

        return true;
    }
}
