package com.spring.blogapp_Springboot.commons.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponses {
    private String message;
    private String details;
}
