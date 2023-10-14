package com.srilekha.employeeservice.service;

import com.srilekha.employeeservice.model.Employee;
import com.srilekha.employeeservice.model.exception.NotFoundException;
import com.srilekha.employeeservice.model.exception.RecordAlreadyExistException;
import com.srilekha.employeeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    /**
     * Persists the Employee.
     * <p>
     * The method to check that there is No Employee with Same id and Saves Employee
     *
     * @param employee the employee object
     * @return the persisted Employee.
     * @throws RecordAlreadyExistException if there is an existing Employee for the employee emailId.
     */
    public Employee createEmployee(Employee employee) throws RecordAlreadyExistException {
        validateEmailIfExists(employee.getEmailId());
        return employeeRepository.save(employee);
    }


    /**
     * Update the Employee.
     * <p>
     * The method to check if Employee exists, validates emailId and Updates record
     *
     * @param employeeId Employee Id of Employee
     * @param updateEmployee the employee details to update
     * @return the persisted Employee.
     * @throws RecordAlreadyExistException if there is an existing Employee for the employee EmailId.
     */
    public Employee updateEmployee(String employeeId, Employee updateEmployee) throws RecordAlreadyExistException,NotFoundException{
        Optional<Employee> employeeOpl = employeeRepository.findById(employeeId);
        final Employee employee;
        if (employeeOpl.isEmpty()) {
            throw new NotFoundException("Employee Doesn't Exist with EmployeeId");
        }else {
            employee = employeeOpl.get();
        }
        employee.setBirtDate(updateEmployee.getBirtDate());
        employee.setHobbies(updateEmployee.getHobbies());
        employee.setFirstName(updateEmployee.getFirstName());
        employee.setLastName(updateEmployee.getLastName());

        if(!employee.getEmailId().equals(updateEmployee.getEmailId())){
            validateEmailIfExists(updateEmployee.getEmailId());
            employee.setEmailId(updateEmployee.getEmailId());
        }
        return employeeRepository.save(employee);
    }

    /**
     * Get Employee record.
     * <p>
     * The method to check if Employee exists, Get Employee record
     *
     * @param employeeId the employee Identifier
     * @return the Employee.
     * @throws NotFoundException if there is an existing Employee for the employeeId.
     */
    public Employee getEmployeeDetails(String employeeId) throws NotFoundException{
        Optional<Employee> employeeOpl = employeeRepository.findById(employeeId);
        if(employeeOpl.isEmpty()){
            throw new NotFoundException("Employee Doesn't Exist with EmployeeId");
        }
        return employeeOpl.get();
    }

    /**
     * Get All Employee records.
     * <p>
     * The method to get all employee records
     *
     * @return the List<Employee>.
     *
     */
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * Delete the Employee.
     * <p>
     * The method to check if Employee exists, deletes the employee record
     *
     * @param employeeId the employee Identifier
     * @throws NotFoundException if there is an existing Employee for the employeeId.
     */
    public void deleteEmployee(String employeeId) throws NotFoundException {
        boolean isExists = employeeRepository.existsById(employeeId);
        if(!isExists){
            throw new NotFoundException("Employee record not found");
        }
        employeeRepository.deleteById(employeeId);
    }

    private void validateEmailIfExists(String emailId) throws RecordAlreadyExistException{
        boolean employeeExist = employeeRepository.existsByEmailId(emailId);
        if (employeeExist) {
            throw new RecordAlreadyExistException("Employee Already Exist with EmailId");
        }
    }
}
