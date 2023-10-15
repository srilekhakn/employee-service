package com.srilekha.employeeservice.service;

import com.srilekha.employeeservice.model.Employee;
import com.srilekha.employeeservice.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@WebMvcTest(EmployeeService.class)
public class EmployeeServiceTest {

    private static Employee employee;
    @MockBean
    EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private KafkaMessagePublisher kafkaMessagePublisher;

    @BeforeAll
    public static void init(){
        employee = new Employee();
        employee.setId("1");
        employee.setEmailId("srilekhakn27gmail.com");
        employee.setFirstName("Srilekha");
        employee.setLastName("KN");
        employee.setBirtDate(new Date());
        List<String> hobbies = new ArrayList<>();
        hobbies.add("Volleyball");
        employee.setHobbies(hobbies);
    }

    @Test
    @DisplayName("Employee Created")
    void TestEmployeeCreated() throws Exception{
        Mockito.when(employeeRepository.existsByEmailId(any())).thenReturn(false);
        Mockito.when(employeeRepository.save(any())).thenReturn(employee);

        Employee createdEmployee = employeeService.createEmployee(employee);
        assertNotNull(createdEmployee);
    }

    @Test
    @DisplayName("Employee Creation error Duplicate email")
    void TestEmployeeCreatedError() throws Exception{
        Mockito.when(employeeRepository.existsByEmailId(any())).thenReturn(true);
        Mockito.when(employeeRepository.save(any())).thenReturn(employee);
        Employee createdEmployee = null;
        try {
            createdEmployee = employeeService.createEmployee(employee);
        }catch (Exception e){
            assertNotNull(e.getMessage());
        }
        assertNull(createdEmployee);
    }

    @Test
    @DisplayName("Employee Updated")
    void TestEmployeeUpdated() throws Exception{
        Mockito.when(employeeRepository.existsByEmailId(any())).thenReturn(false);
        Mockito.when(employeeRepository.findById(anyString())).thenReturn(Optional.ofNullable(employee));
        Mockito.when(employeeRepository.save(any())).thenReturn(employee);

        Employee createdEmployee = employeeService.updateEmployee(employee.getId(), employee);
        assertNotNull(createdEmployee);
    }

    @Test
    @DisplayName("Employee Update error Invalid employee Id")
    void TestEmployeeUpdatedError() throws Exception{
        Mockito.when(employeeRepository.existsByEmailId(any())).thenReturn(false);
        Mockito.when(employeeRepository.findById(anyString())).thenReturn(Optional.ofNullable(null));
        Mockito.when(employeeRepository.save(any())).thenReturn(employee);
        Employee updatedEmployee = null;
        try {
            updatedEmployee = employeeService.updateEmployee(employee.getId(),employee);
        }catch (Exception e){
            assertNotNull(e.getMessage());
        }
        assertNull(updatedEmployee);
    }

    @Test
    @DisplayName("Employee Details")
    void TestGetEmployeeDetails() throws Exception{
        Mockito.when(employeeRepository.existsByEmailId(any())).thenReturn(false);
        Mockito.when(employeeRepository.findById(anyString())).thenReturn(Optional.ofNullable(employee));

        Employee employeeDetails = employeeService.getEmployeeDetails(employee.getId());
        assertNotNull(employeeDetails);
    }

    @Test
    @DisplayName("Employee details error Invalid employee Id")
    void TestGetEmployeeDetailsError() throws Exception{
        Mockito.when(employeeRepository.existsByEmailId(any())).thenReturn(false);
        Mockito.when(employeeRepository.findById(anyString())).thenReturn(Optional.ofNullable(null));
        Employee employeeDetails = null;
        try {
            employeeDetails = employeeService.getEmployeeDetails(employee.getId());
        }catch (Exception e){
            assertNotNull(e.getMessage());
        }
        assertNull(employeeDetails);
    }

    @Test
    @DisplayName("Employee Delete")
    void TestDeleteEmployee() throws Exception{
        Mockito.when(employeeRepository.existsById(anyString())).thenReturn(true);
        try {
            employeeService.deleteEmployee(employee.getId());
        }catch (Exception e){
            assertNotNull(e.getMessage());
        }
    }

    @Test
    @DisplayName("Employee delete error Invalid employee Id")
    void TestDeleteEmployeeError() throws Exception{
        Mockito.when(employeeRepository.existsById(anyString())).thenReturn(false);
        try {
            employeeService.deleteEmployee(employee.getId());
        }catch (Exception e){
            assertNotNull(e.getMessage());
        }
    }
}
