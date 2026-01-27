package com.codingsrv2.LearningRESTAPIs.service;

import com.codingsrv2.LearningRESTAPIs.dto.EmployeeDTO;
import com.codingsrv2.LearningRESTAPIs.entity.EmployeeEntity;
import com.codingsrv2.LearningRESTAPIs.exceptions.ResourceNotFoundException;
import com.codingsrv2.LearningRESTAPIs.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeDTO getEmployeeById(Integer employeeId) {
        EmployeeEntity employeeEntity = employeeRepository
                .findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id: " + employeeId));
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public List<EmployeeDTO> getEmployee() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO inputEmployee) {
        EmployeeEntity toSaveEntity = modelMapper.map(inputEmployee, EmployeeEntity.class);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(toSaveEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(Integer employeeId, EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(employeeId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEmployeeEntity, EmployeeDTO.class);
    }

    public boolean isExistByEmployeeId(Integer employeeId){
        return employeeRepository.existsById(employeeId);
    }

    public boolean deleteEmployeeById(Integer employeeId) {
        boolean exist = isExistByEmployeeId(employeeId);
        if (!exist) return false;
        employeeRepository.deleteById(employeeId);
        return true;
    }
//
//    public EmployeeDTO updatePartialByEmployeeId(Integer employeeId, Map<String, Object> updates) {
//        boolean exist = isExistByEmployeeId(employeeId);
//        if (!exist) return null;
//        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
//        updates.forEach((field, value) -> {
//            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class, field);
//            fieldToBeUpdated.setAccessible(true);
//            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
//        });
//        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
//    }

}
