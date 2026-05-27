package com.codingsrv.ServiceLayerUnitTestingWithMockito.services.impl;

import com.codingsrv.ServiceLayerUnitTestingWithMockito.dto.EmployeeDto;
import com.codingsrv.ServiceLayerUnitTestingWithMockito.entities.Employee;
import com.codingsrv.ServiceLayerUnitTestingWithMockito.exceptions.ResourceNotFoundException;
import com.codingsrv.ServiceLayerUnitTestingWithMockito.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

//@Import(TestContainerConfiguration.class) // currently i am using H2 as test database
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@ExtendWith(MockitoExtension.class) // to enable Mockito support in a test class.
class EmployeeServiceImplTest {

    @Spy
    private ModelMapper modelMapper;

    @Mock  //  to create a mock (fake) object of dependency(a class or interface)
    private EmployeeRepository employeeRepository;


    @InjectMocks // to automatically inject mock dependencies into it.
    private EmployeeServiceImpl employeeService;

    // first create a mock(fake) employee and mockEmployeeDto because we don't want to test on actual repository.
    private Employee mockEmployee;
    private EmployeeDto mockEmployeeDto;

    @BeforeEach
    void setUp(){
       mockEmployee =  Employee.builder()
                .id(1L)
                .name("saurav")
                .email("srv@gmail.com")
                .salary(20000L)
                .build();

        mockEmployeeDto = modelMapper.map(mockEmployee, EmployeeDto.class);

    }


    @Test // happy test case
    void testGetEmployeeById_whenEmployeeIdIsPresent_thenReturnEmployeeDto(){
        // assign
        Long id = mockEmployee.getId();
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(mockEmployee)); // stubbing

        // act
        EmployeeDto employeeDto = employeeService.getEmployeeById(id); // but we want to test on actual Service

        // assert
        assertThat(employeeDto).isNotNull();
        assertThat(employeeDto.getId()).isEqualTo(mockEmployee.getId());
        assertThat(employeeDto.getEmail()).isEqualTo(mockEmployee.getEmail());
        verify(employeeRepository).findById(id); // it verifies on "employeeRepository" mock, findById(id) method is called or not.
        //verification of method call with specific verification mode.
        verify(employeeRepository, times(1)).findById(1L); // verifies whether method was called 1 times or not.
        verify(employeeRepository, atLeast(1)).findById(1L); // verifies whether method was called at least 1 times( 1 or more times).
        verify(employeeRepository, atMost(1)).findById(1L); // // verifies whether method was called at most 1 times( 1 or less times).
        verify(employeeRepository, only()).findById(1L); // verifies only this method i.e. findById(1L) was called.

    }

    @Test  // sad test case
    void testGetEmployeeById_whenEmployeeIsNotPresent_thenThrowException(){
        // arrange
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());
        // act and assert
        assertThatThrownBy(()->employeeService.getEmployeeById(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee not found with id: 1");

        verify(employeeRepository).findById(1L);
    }



    @Test  // happy test case
    void testCreateNewEmployee_whenValidEmployee_thenCreateNewEmployee(){
        //assign
        when(employeeRepository.findByEmail(anyString())).thenReturn(List.of());  // stubbing
        when(employeeRepository.save(any())).thenReturn(mockEmployee);           // stubbing

        // act
        EmployeeDto employeeDto = employeeService.createNewEmployee(mockEmployeeDto);

        // assert

        assertThat(employeeDto).isNotNull();
        assertThat(employeeDto.getEmail()).isEqualTo(mockEmployeeDto.getEmail());

        //steps for ArgumentCaptor
        // create an ArgumentCaptor for the Employee
        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class); // instantiate employee based captor.
        // verify the save() method is called and capture the argument
        verify(employeeRepository).save(employeeArgumentCaptor.capture()); // capture the argument
        // retrieve the capture argument
        Employee capturedEmployee = employeeArgumentCaptor.getValue();
        // perform assertions on the captured argument
        assertThat(capturedEmployee.getEmail()).isEqualTo(mockEmployee.getEmail());

    }

    @Test  // sad test case
    void testCreateNewEmployee_whenAttemptingToCreateEmployeeWithExistingEmail_thenThrowException(){
        // arrange
        when(employeeRepository.findByEmail(anyString())).thenReturn(List.of(mockEmployee));
        // act and assert
        assertThatThrownBy(()-> employeeService.createNewEmployee(mockEmployeeDto))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Employee already exists with email: "+mockEmployee.getEmail());

        verify(employeeRepository).findByEmail(mockEmployeeDto.getEmail());
        verify(employeeRepository, never()).save(any());

    }




        @Test  // sad test case
        void testUpdateEmployee_whenEmployeeDoesNotExist_thenThrowException(){
            // arrange
            when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());
            // act and assert
            assertThatThrownBy(()->employeeService.updateEmployee(1L, mockEmployeeDto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee not found with id: 1");
            verify(employeeRepository).findById(1L);
            verify(employeeRepository, never()).save(any());

    }

    @Test  // sad test case
    void testUpdateEmployee_whenAttemptingToUpdateEmail_thenThrowException(){
        // assign
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(mockEmployee));
        mockEmployee.setName("Random name");
        mockEmployee.setEmail("random@gmail.com");
        // act and assert
        assertThatThrownBy(()->employeeService.updateEmployee(mockEmployeeDto.getId(),mockEmployeeDto))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("The email of the employee cannot be updated");

        verify(employeeRepository).findById(mockEmployeeDto.getId());
        verify(employeeRepository,never()).save(any());

    }

    @Test  // happy test cases
    void testUpdateEmployee_whenValidEmployee_thenUpdateEmployee(){
        // assign for findById() method
        when(employeeRepository.findById(mockEmployeeDto.getId())).thenReturn(Optional.of(mockEmployee));
        mockEmployee.setName("Random");
        mockEmployee.setSalary(14500L);
        // now assign for save() method but now mockEmployee is updated
        Employee newEmployee = modelMapper.map(mockEmployee, Employee.class);
        when(employeeRepository.save(any(Employee.class))).thenReturn(newEmployee);

        // act
        EmployeeDto updatedEmployeeDto = employeeService.updateEmployee(mockEmployeeDto.getId(),mockEmployeeDto);

        // assert
        assertThat(updatedEmployeeDto).isEqualTo(mockEmployeeDto);
        verify(employeeRepository).findById(1L);
        verify(employeeRepository).save(any());

    }


    @Test
    void testDeleteEmployee_whenEmployeeDoesNotExist_thenThrowException(){
        // assign
        when(employeeRepository.existsById(1L)).thenReturn(false);
        // act and assert
        assertThatThrownBy(()->employeeService.deleteEmployee(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee not found with id: 1");

        verify(employeeRepository,never()).deleteById(anyLong());

    }

    @Test
    void testDeleteEmployee_whenEmployeeIsFound_thenDeleteEmployee(){
        // assign
        when(employeeRepository.existsById(1L)).thenReturn(true);

        // act and assert
        assertThatCode(()-> employeeService.deleteEmployee(1l))
                .doesNotThrowAnyException();

        verify(employeeRepository).deleteById(1L);

    }

}