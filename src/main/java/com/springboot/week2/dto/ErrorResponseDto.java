package com.springboot.week2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {
    private int status;
    private String message;
    private String error;
    private LocalDateTime timestamp;
    private String path;
}

