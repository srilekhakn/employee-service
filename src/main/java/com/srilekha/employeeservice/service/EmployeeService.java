package com.srilekha.employeeservice.service;

import com.srilekha.employeeservice.model.Employee;
import com.srilekha.employeeservice.model.events.EmployeeEvent;
import com.srilekha.employeeservice.model.events.EventType;
import com.srilekha.employeeservice.model.exception.NotFoundException;
import com.srilekha.employeeservice.model.exception.RecordAlreadyExistException;
import com.srilekha.employeeservice.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private KafkaMessagePublisher kafkaMessagePublisher;


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
        Employee savedEmployee = employeeRepository.save(employee);
        log.info("Employee Saved with id ${}", savedEmployee.getId());
        try {
            EmployeeEvent employeeEvent = new EmployeeEvent(UUID.randomUUID().toString(),savedEmployee.getId(),"Creating Employee", EventType.CREATED);
            kafkaMessagePublisher.sendMessageToTopicEmployeeEvents(employeeEvent);
            log.info("Create Event published");
        }catch (Exception ex){
            log.info(ex.getMessage());
        }
        return savedEmployee;
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
        Employee updatedEmployee = employeeRepository.save(employee);
        log.info("Employee Updated with id ${}", updatedEmployee.getId());

        try {
            EmployeeEvent employeeUpdatedEvent = new EmployeeEvent(UUID.randomUUID().toString(),employeeId,"Updating Employee", EventType.UPDATED);
            kafkaMessagePublisher.sendMessageToTopicEmployeeEvents( employeeUpdatedEvent);
            log.info("Update Event published");
        }catch (Exception ex){
            log.info(ex.getMessage());
        }

        return updatedEmployee;
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
        log.info("Employee Deleted with id ${}",employeeId);
        try {
            EmployeeEvent employeeDeletedEvent = new EmployeeEvent(UUID.randomUUID().toString(),employeeId,"Deleting Employee",EventType.DELETED);
            kafkaMessagePublisher.sendMessageToTopicEmployeeEvents(employeeDeletedEvent);
            log.info("Delete Event published");
        }catch (Exception ex){
            log.info(ex.getMessage());
        }
    }

    public boolean checkIfEmployeeExists(String employeeId){
        return employeeRepository.existsById(employeeId);
    }

    private void validateEmailIfExists(String emailId) throws RecordAlreadyExistException{
        boolean employeeExist = employeeRepository.existsByEmailId(emailId);
        if (employeeExist) {
            throw new RecordAlreadyExistException("Employee Already Exist with EmailId");
        }
    }
}
