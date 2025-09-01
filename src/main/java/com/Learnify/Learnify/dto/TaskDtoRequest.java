package com.Learnify.Learnify.dto;

import com.Learnify.Learnify.enums.Priority;
import com.Learnify.Learnify.enums.Status;
import lombok.Data;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Data
public class TaskDtoRequest {

    @NotBlank(message = "Title cannot be empty.")
    private String title;

    @NotBlank(message = "Description cannot be empty.")
    private String description;

    @NotNull(message = "Status must be provided.")
    private Status status;

    @NotNull(message = "Priority must be provided.")
    private Priority priority;

    @FutureOrPresent(message = "Deadline cannot be in the past.")
    private LocalDateTime deadline;
}
