package com.srilekha.employeeservice.controller;

import com.srilekha.employeeservice.model.Employee;
import com.srilekha.employeeservice.model.dto.EmployeeDto;
import com.srilekha.employeeservice.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Insert a new Employee", description = "Persist a new Employee and generate its id.")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Employee created")})
    @PostMapping(produces = {"application/json"}, consumes = {"application/json"})
    ResponseEntity<EmployeeDto> createEmployee(@RequestBody final EmployeeDto employeeDto) {
        log.info("In Create Employee..");
        Employee employee = employeeService.createEmployee(EmployeeDto.toEmployee(employeeDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(EmployeeDto.fromEmployee(employee));
    }

    @Operation(summary = "Updates Employee", description = "Persists the updated employee details")
    @ApiResponses(value = {@ApiResponse(responseCode = "202", description = "Employee Updated")})
    @PutMapping(value = "/{id}", produces = {"application/json"}, consumes = {"application/json"})
    ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") String employeeId, @Valid @RequestBody final EmployeeDto employeeDto) {
        log.info("In Update Employee..");
        Employee employee = employeeService.updateEmployee(employeeId, EmployeeDto.toEmployee(employeeDto));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(EmployeeDto.fromEmployee(employee));
    }

    @Operation(summary = "Get Employee", description = "Gets Employee details by Id")
    @ApiResponses(value = {@ApiResponse(responseCode = "202", description = "Record fetched")})
    @GetMapping(value = "/{id}", produces = {"application/json"})
    ResponseEntity<EmployeeDto> getEmployee(@PathVariable("id") String employeeId) {
        log.info("In Get Employee..");
        Employee employee = employeeService.getEmployeeDetails(employeeId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(EmployeeDto.fromEmployee(employee));
    }

    @Operation(summary = "Get Employees", description = "Gets All Employee details")
    @ApiResponses(value = {@ApiResponse(responseCode = "202", description = "All records fetched")})
    @GetMapping(produces = {"application/json"})
    ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        log.info("In Get all Employees..");
        List<Employee> employeeList = employeeService.getAllEmployees();
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        for (Employee employee : employeeList) {
            employeeDtoList.add(EmployeeDto.fromEmployee(employee));
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeDtoList);
    }

    @Operation(summary = "Delete Employee", description = "Deletes Employee record")
    @ApiResponses(value = {@ApiResponse(responseCode = "202", description = "Delete successful")})
    @DeleteMapping(value = "/{id}", produces = {"application/json"})
    ResponseEntity<Void> deleteEmployee(@PathVariable("id") String employeeId) {
        log.info("In Delete Employee..");
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


}
