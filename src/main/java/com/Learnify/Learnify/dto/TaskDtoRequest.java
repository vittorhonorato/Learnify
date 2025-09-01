package com.Learnify.Learnify.dto;

import com.Learnify.Learnify.enums.Priority;
import com.Learnify.Learnify.enums.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDtoRequest {
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private LocalDateTime deadline;
}
