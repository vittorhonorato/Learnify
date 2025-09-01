package com.Learnify.Learnify.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


public record ErrorResponse(HttpStatus status, int statusCode, String message, LocalDateTime timestamp) {
}
