package com.zoheeb.springboot.repository;

import com.zoheeb.springboot.model.Employee;
// static + .assertThat pour sup Assertions
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    //JUnit test for save employee operation
    @DisplayName("JUnit test for save employee operation")
    @Test
    public void givenEmployee_whenSave_thenReturnSavedEmployee() {


        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Zoheeb")
                .lastName("Ishaque")
                .email("zoheeb@gmail.com")
                .build();

        //when - action or behavior that we are going to test
        Employee savedEmployee = employeeRepository.save(employee);

        //then - verify the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);


    }


    //JUnit test for

    @Test
    @DisplayName("JUnit test for get all employees operation")
    public void givenEmployeesList_whenFindAll_thenEmployeesList() {
        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Zoheeb")
                .lastName("Ishaque")
                .email("zoheeb@gmail.com")
                .build();


        Employee employee1 = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@gmail.com")
                .build();

        Employee employee2 = Employee.builder()
                .firstName("Florent")
                .lastName("Perrier")
                .email("Florent@gmail.com")
                .build();

        employeeRepository.save(employee);
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        //when - action or behavior that we are going to test
        List<Employee> employeeList = employeeRepository.findAll();


        //then - verify the output
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(3);
    }


    //JUnit test for get employee by id operation
    @Test
    @DisplayName("JUnit test for get employee by id operation")
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {
        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Zoheeb")
                .lastName("Ishaque")
                .email("zoheeb@gmail.com")
                .build();
        employeeRepository.save(employee);

        //when - action or behavior that we are going to test
        Employee employeeDB = employeeRepository.findById(employee.getId()).get();

        //then - verify the output
        assertThat(employeeDB).isNotNull();
    }


    //JUnit test for get employee by Email operation
    @Test
    @DisplayName("JUnit test for get employee by Email operation")
    public void givenEmployeeEmail_whenFindbyEmail_thenEmployeeObject() {
        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Zoheeb")
                .lastName("Ishaque")
                .email("zoheeb@gmail.com")
                .build();
        employeeRepository.save(employee);

        //when - action or behavior that we are going to test
        Employee employeeDB = employeeRepository.findByEmail(employee.getEmail()).get();
        //then - verify the output
        assertThat(employeeDB).isNotNull();
    }

    //JUnit test for update employee operation
    @Test
    @DisplayName("JUnit test for update employee operation")
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {
        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Zoheeb")
                .lastName("Ishaque")
                .email("zoheeb@gmail.com")
                .build();
        employeeRepository.save(employee);

        //when - action or behavior that we are going to test
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
        savedEmployee.setEmail("ishaque@gmail.com");
        savedEmployee.setLastName("Ishaq");
        Employee updatedEmployee = employeeRepository.save(savedEmployee);

        //then - verify the output
        assertThat(updatedEmployee.getEmail()).isEqualTo("ishaque@gmail.com");
        assertThat(updatedEmployee.getLastName()).isEqualTo("Ishaq");

    }
        //JUnit test for delete employee operation
            @Test
            @DisplayName("JUnit test for delete employee operation")
            public  void givenEmployeeObject_whenDelete_thenRemoveEmployee(){
                //given - precondition or setup
                Employee employee = Employee.builder()
                        .firstName("Zoheeb")
                        .lastName("Ishaque")
                        .email("zoheeb@gmail.com")
                        .build();
                employeeRepository.save(employee);

                //when - action or behavior that we are going to test
                employeeRepository.deleteById(employee.getId());
                Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());
                //then - verify the output
                assertThat(employeeOptional).isEmpty();
            }

}
