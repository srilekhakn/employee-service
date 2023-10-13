package com.srilekha.employeeservice.repository;

import com.srilekha.employeeservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query("select e from Exercise e where e.userId in :userIds and (e.startTime > :startDate and e.startTime < :currentDate)")
    List<Employee> findByUserIds(@Param("userIds") Collection<Long> userIds, @Param("startDate") LocalDateTime startDate, @Param("currentDate") LocalDateTime currentDate);

    List<Employee> findByUserId(Long userId);
}
