package com.Learnify.Learnify.dto;

import com.Learnify.Learnify.entity.Task;

public class TaskMapper {
    public static TaskDtoResponse toDto(Task task) {
        TaskDtoResponse dto = new TaskDtoResponse();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setPriority(task.getPriority());
        dto.setDeadline(task.getDeadline());
        return dto;
    }

    public static Task toEntity(TaskDtoRequest dto) {
       Task task = new Task();
        task.setTitle(dto.title);
        task.setDescription(dto.description);
        task.setStatus(dto.status);
        task.setPriority(dto.priority);
        task.setDeadline(dto.deadline);
        return task;
    }
}
