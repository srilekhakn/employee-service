package com.srilekha.employeeservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Setter
@Getter
public class Employee {


    /**
     * id of the employee
     */
    @Id
    private Long id;

    /**
     * EmailId of the Employee.
     */
    private String emailId;

    /**
     * First Name of The Employee.
     */
    private String firstName;

    /**
     * Last Name of the Employee
     */
    private String lastName;

    /**
     * Birth Date of the Employee
     */
    private LocalDateTime birtDate;

    /**
     * Hobbies of the Employee
     */
    private List<String> hobbies;

}
