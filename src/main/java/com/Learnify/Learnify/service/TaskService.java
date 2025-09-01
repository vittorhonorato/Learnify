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
        Task taskEntity = TaskMapper.toEntity(taskDtoRequest);
        Task savedTask = taskRepository.save(taskEntity);

        return TaskMapper.toDto(savedTask);
    }

    public TaskDtoResponse updateTask(UUID id, TaskDtoRequest taskDtoRequest) {
        Task existingTask = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Cannot update because id: " + id + " does not exist."));

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
