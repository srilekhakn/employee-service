package com.srilekha.employeeservice.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponseDTO {
        private String message;
        public ErrorResponseDTO(String message){
                this.message = message;
        }
}
