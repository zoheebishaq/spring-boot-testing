package com.zoheeb.springboot.repository;

import com.zoheeb.springboot.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    //JUnit test for save employee operation
    @Test
    public void givenEmployee_whenSave_thenReturnSavedEmployee(){

        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Zoheeb")
                .lastName("Ishaque")
                .email("zoheeb@gmail.com")
                .build();

        //when - action or behavior that we are going to test
        Employee savedEmployee =employeeRepository.save(employee);

        //then - verify the output
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.getId()).isGreaterThan(0);


    }

}
