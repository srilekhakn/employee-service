package com.srilekha.employeeservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.srilekha.employeeservice.model.Employee;
import com.srilekha.employeeservice.model.dto.EmployeeDto;
import com.srilekha.employeeservice.model.exception.RecordAlreadyExistException;
import com.srilekha.employeeservice.repository.EmployeeRepository;
import com.srilekha.employeeservice.service.EmployeeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    static EmployeeDto employeeDto;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    EmployeeService employeeService;

    @MockBean
    EmployeeRepository employeeRepository;

    @BeforeAll
    public static void init(){
        employeeDto = new EmployeeDto();
        employeeDto.setId("1");
        employeeDto.setEmailId("srilekhakn27gmail.com");
        employeeDto.setFirstName("Srilekha");
        employeeDto.setLastName("KN");
        employeeDto.setBirtDate(new Date());
        List<String> hobbies = new ArrayList<>();
        hobbies.add("Volleyball");
        employeeDto.setHobbies(hobbies);
    }

    @Test
    void TestEmployeeCreate() throws Exception {
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmailId(employeeDto.getEmailId());
        employee.setBirtDate(employeeDto.getBirtDate());
        employee.setHobbies(employeeDto.getHobbies());

        Mockito.when(employeeService.createEmployee(any())).thenReturn(employee);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(employeeDto));

        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.emailId").value(employeeDto.getEmailId()))
                .andExpect(jsonPath("$.id").value(employeeDto.getId()));

    }

    @Test
    @DisplayName("Invalid Case")
    void TestEmployeeCreateInvalidCase() throws Exception {
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmailId(employeeDto.getEmailId());
        employee.setBirtDate(employeeDto.getBirtDate());
        employee.setHobbies(employeeDto.getHobbies());

        Mockito.when(employeeService.createEmployee(any())).thenThrow(new RecordAlreadyExistException("Email Already Exists"));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(employeeDto));

        mockMvc.perform(mockRequest)
                .andExpect(status().isAlreadyReported())
                .andExpect(jsonPath("$.message").value("Email Already Exists"));

    }

    @Test
    void TestEmployeeUpdate() throws Exception {
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmailId(employeeDto.getEmailId());
        employee.setBirtDate(employeeDto.getBirtDate());
        employee.setHobbies(employeeDto.getHobbies());

        Mockito.when(employeeService.updateEmployee(any(),any())).thenReturn(employee);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/employee/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(employeeDto));

        mockMvc.perform(mockRequest)
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.emailId").value(employeeDto.getEmailId()))
                .andExpect(jsonPath("$.id").value(employeeDto.getId()));

    }

    @Test
    void TestGetEmployeeDetails() throws Exception {
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmailId(employeeDto.getEmailId());
        employee.setBirtDate(employeeDto.getBirtDate());
        employee.setHobbies(employeeDto.getHobbies());

        Mockito.when(employeeService.getEmployeeDetails(any())).thenReturn(employee);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get("/employee/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.emailId").value(employeeDto.getEmailId()))
                .andExpect(jsonPath("$.id").value(employeeDto.getId()));

    }

    @Test
    void TestGetAllEmployees() throws Exception {
        List<Employee> employees = new ArrayList<>();
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmailId(employeeDto.getEmailId());
        employee.setBirtDate(employeeDto.getBirtDate());
        employee.setHobbies(employeeDto.getHobbies());
        employees.add(employee);
        Employee employee1 = new Employee();
        employee1.setId(employeeDto.getId());
        employee1.setFirstName(employeeDto.getFirstName());
        employee1.setLastName(employeeDto.getLastName());
        employee1.setEmailId(employeeDto.getEmailId());
        employee1.setBirtDate(employeeDto.getBirtDate());
        employee1.setHobbies(employeeDto.getHobbies());
        employees.add(employee1);
        Mockito.when(employeeService.getAllEmployees()).thenReturn(employees);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .get("/employee")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void TestDeleteEmployees() throws Exception {
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmailId(employeeDto.getEmailId());
        employee.setBirtDate(employeeDto.getBirtDate());
        employee.setHobbies(employeeDto.getHobbies());


        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .delete("/employee/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(mockRequest)
                .andExpect(status().is2xxSuccessful());
    }

}
