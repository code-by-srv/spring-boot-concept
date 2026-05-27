package com.codingsrv.RepositoryUnitTesting.repositories;

import com.codingsrv.RepositoryUnitTesting.TestContainerConfiguration;
import com.codingsrv.RepositoryUnitTesting.entities.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.TimeZone;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@Import(TestContainerConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    // before testing, first create an employee, so we can test upon it.
    private Employee employee;
    @BeforeEach
    void setUp(){
         employee = Employee.builder()
                .id(1L)
                .name("saurav")
                .email("srv@gmail.com")
                .salary(10000L)
                .build();
    }

    //Java (your system) uses: Asia/Calcutta (old name) but PostgreSQL expects: Asia/Kolkata (correct modern name)
    @BeforeAll
    static void fixTimezone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
    }

    @Test
    void testFindByEmail_whenEmailIsFound_thenReturnEmployee() {

        // Arrange(Given)
        employeeRepository.save(employee); // we first save the created employee in embedded db i.e. H2

        //Act(When)
        List<Employee> employeeList = employeeRepository.findByEmail(employee.getEmail()); // here we call the method which we want to test

        //Assert(Then)
        assertThat(employeeList).isNotNull();
        assertThat(employeeList).isNotEmpty();
        assertThat(employeeList.get(0).getEmail()).isEqualTo(employee.getEmail());

    }


    @Test
    void testFindByEmail_whenEmailIsNotFound_thenReturnEmptyEmployeeList() {

        // Arrange(Given)
        String email = "NotPresent.123@gmail.com";

        //Act(When)
        List<Employee> employeeList = employeeRepository.findByEmail(email);

        //Assert(Then)
        assertThat(employeeList).isNotNull();
        assertThat(employeeList).isEmpty();

    }
}