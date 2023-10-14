package com.srilekha.employeeservice.model.dto;

import com.srilekha.employeeservice.model.Employee;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class EmployeeDto {
    /**
     * id of the employee
     */
    private String id;

    /**
     * EmailId of the Employee.
     */
    @Pattern(regexp = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")
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

    public static Employee toEmployee(final EmployeeDto dto) {
        final Employee e = new Employee();
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

    public static EmployeeDto fromEmployee(final Employee e) {
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
