package com.Learnify.Learnify.service;

import com.Learnify.Learnify.dto.TaskDtoRequest;
import com.Learnify.Learnify.dto.TaskDtoResponse;
import com.Learnify.Learnify.dto.TaskMapper;
import com.Learnify.Learnify.entity.Task;
import com.Learnify.Learnify.exception.InvalidTaskException;
import com.Learnify.Learnify.exception.TaskNotFoundException;
import com.Learnify.Learnify.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskDtoResponse> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();

        return tasks.stream().map(TaskMapper::toDto).toList();
    }

    public TaskDtoResponse getTaskById(UUID id) {
        Task taskById = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));

        return TaskMapper.toDto(taskById);
    }

    public TaskDtoResponse saveTask(TaskDtoRequest taskDtoRequest) {
        if (taskDtoRequest.getTitle() == null || taskDtoRequest.getTitle().isEmpty()) {
            throw new InvalidTaskException("Title cannot be empty.");
        }

        if (taskDtoRequest.getDescription() == null || taskDtoRequest.getDescription().isEmpty()) {
            throw new InvalidTaskException("Description cannot be empty.");
        }

        if (taskDtoRequest.getStatus() == null) {
            throw new InvalidTaskException("Status must be provided.");
        }

        if (taskDtoRequest.getPriority() == null) {
            throw new InvalidTaskException("Priority must be provided.");
        }

        if (taskDtoRequest.getDeadline() != null && taskDtoRequest.getDeadline().isBefore(LocalDateTime.now())) {
            throw new InvalidTaskException("Deadline cannot be in the past.");
        }

        Task taskEntity = TaskMapper.toEntity(taskDtoRequest);
        Task savedTask = taskRepository.save(taskEntity);
        return TaskMapper.toDto(savedTask);
    }

    public TaskDtoResponse updateTask(UUID id, TaskDtoRequest taskDtoRequest) {
        Task existingTask = taskRepository.findById(id).orElseThrow();

        existingTask.setTitle(taskDtoRequest.getTitle());
        existingTask.setDescription(taskDtoRequest.getDescription());
        existingTask.setStatus(taskDtoRequest.getStatus());
        existingTask.setPriority(taskDtoRequest.getPriority());
        existingTask.setDeadline(taskDtoRequest.getDeadline());

        Task updatedTask = taskRepository.save(existingTask);
        return TaskMapper.toDto(updatedTask);
    }

    public void deleteTask(UUID id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("Cannot delete because id: " + id + " does not exist.");
        }

        taskRepository.deleteById(id);
    }
}
