package com.srilekha.employeeservice.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document("employee_service")
@NoArgsConstructor
@Setter
@Getter
public class Employee {


    /**
     * id of the employee
     */
    @Id
    private String id;

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
    private Date birtDate;

    /**
     * Hobbies of the Employee
     */
    private List<String> hobbies;

    public Employee(String emailId, String firstName, String lastName, Date birtDate, List<String> hobbies) {
        this.emailId = emailId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birtDate = birtDate;
        this.hobbies = hobbies;
    }
}
