package com.srilekha.employeeservice.service;

import com.srilekha.employeeservice.model.events.EmployeeEvent;
import com.srilekha.employeeservice.model.events.EventType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaMessageConsumer {
    @Autowired
    EmployeeService employeeService;
    @KafkaListener(topics = "employee-events", groupId = "employee-group", containerFactory = "employeeEventListener")
    public void deleteEmployeeEvent(EmployeeEvent message){
       try {
           if(message.getEventType().equals(EventType.DELETED) && employeeService.checkIfEmployeeExists(message.getEmployeeId())){
               log.info("Delete employee triggered");
               employeeService.deleteEmployee(message.getEmployeeId());
           }
       }catch (Exception ex){
           log.info(ex.getMessage());
       }

    }
}
