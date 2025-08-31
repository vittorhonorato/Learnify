package com.Learnify.Learnify.dto;

import com.Learnify.Learnify.enums.Priority;
import com.Learnify.Learnify.enums.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDtoRequest {
    public String title;
    public String description;
    public Status status;
    public Priority priority;
    public LocalDateTime deadline;
}
