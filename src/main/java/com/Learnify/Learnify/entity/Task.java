package com.Learnify.Learnify.entity;

import com.Learnify.Learnify.enums.Priority;
import com.Learnify.Learnify.enums.Status;
import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private LocalDateTime deadline;

}
