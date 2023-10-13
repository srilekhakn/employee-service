package com.srilekha.employeeservice.model.dto;

import com.srilekha.employeeservice.model.Employee;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class EmployeeDto {
    /**
     * id of the employee
     */
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

    public static Employee toEmployee(final EmployeeDto dto) {
        final Employee e = new Employee();
        e.setId(dto.id);
        if (dto.emailId != null) {
            e.setEmailId(dto.emailId.trim());
        }
        e.setFirstName(dto.firstName);
        e.setLastName(dto.lastName);
        if (dto.birtDate != null) {
            e.setBirtDate(dto.birtDate);
        }
        e.setHobbies(dto.hobbies);
        return e;
    }

    public static EmployeeDto fromExercise(final Employee e) {
        final EmployeeDto dto = new EmployeeDto();
        dto.id = e.getId();
        dto.emailId = e.getEmailId();
        dto.firstName = e.getFirstName();
        dto.lastName = e.getLastName();
        dto.birtDate = e.getBirtDate();
        dto.hobbies = e.getHobbies();
        return dto;
    }

}
