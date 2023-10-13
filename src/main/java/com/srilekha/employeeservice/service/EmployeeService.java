package com.srilekha.employeeservice.service;

import com.srilekha.employeeservice.model.Employee;
import com.srilekha.employeeservice.model.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee);
    Employee updateEmployee(Long uuid, Employee updateEmployee);
    Employee getEmployeeDetails(Long uuid);
    List<Employee> getAllEmployees();
    boolean deleteEmployee(Long uuid);

}
