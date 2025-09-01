package com.Learnify.Learnify.controller;

import com.Learnify.Learnify.dto.TaskDtoRequest;
import com.Learnify.Learnify.dto.TaskDtoResponse;
import com.Learnify.Learnify.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskDtoResponse>> getAllTasks() {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public TaskDtoResponse getTaskById(@PathVariable UUID id) {
        return taskService.getTaskById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<TaskDtoResponse> createTask(@RequestBody TaskDtoRequest taskDtoRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.saveTask(taskDtoRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TaskDtoResponse> updateTask(@PathVariable UUID id, @RequestBody TaskDtoRequest taskDtoRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateTask(id, taskDtoRequest));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
