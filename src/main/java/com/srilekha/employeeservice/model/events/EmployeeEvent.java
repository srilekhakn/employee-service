package com.srilekha.employeeservice.model.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeEvent {
    private String eventId;
    private String employeeId;
    private String comments;
    private EventType eventType;
}
