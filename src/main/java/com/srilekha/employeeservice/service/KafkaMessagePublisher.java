package com.srilekha.employeeservice.service;

import com.srilekha.employeeservice.model.events.EmployeeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaMessagePublisher {

    @Autowired
    KafkaTemplate<String,EmployeeEvent> template;

    public void sendMessageToTopicEmployeeEvents(EmployeeEvent message){
        String topicName = "employee-events";
        CompletableFuture<SendResult<String, EmployeeEvent>> future =  template.send(topicName, message);
        future.whenComplete((result,ex)->{
            if(ex == null){
                System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }else {
                System.out.println("Unable to Sent message=[" + message + "] due to : " + ex.getMessage());
            }
        });
    }
}
