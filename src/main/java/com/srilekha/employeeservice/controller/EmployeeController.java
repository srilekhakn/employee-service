package com.srilekha.employeeservice.controller;

import com.srilekha.employeeservice.model.dto.EmployeeDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @PostMapping(produces = {"application/json"}, consumes = {"application/json"})
    ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody final EmployeeDto employeeDto){
        try {

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @PutMapping(produces = {"application/json"}, consumes = {"application/json"})
    ResponseEntity<EmployeeDto> updateEmployee( @RequestBody final EmployeeDto employeeDto){
        try {
            return
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @GetMapping(produces = {"application/json"})
    ResponseEntity<EmployeeDto> getEmployee(@PathVariable String employeeId){
        try {

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping(produces = {"application/json"})
    ResponseEntity<EmployeeDto> getEmployee(@PathVariable String employeeId){
        try {

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping(produces = {"application/json"})
    ResponseEntity<EmployeeDto> getEmployee(@PathVariable String employeeId){
        try {

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


}
