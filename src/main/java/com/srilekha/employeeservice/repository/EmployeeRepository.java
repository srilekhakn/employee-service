package com.srilekha.employeeservice.repository;

import com.srilekha.employeeservice.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends MongoRepository<Employee, UUID> {

    boolean existsByEmailId(String emailId);

    Optional<Employee> findById(String uuid);

    boolean existsById(String employeeId);

    void deleteById(String employeeId);
}
