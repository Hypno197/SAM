package com.generation.italy.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.italy.model.Task;
import com.generation.italy.repository.StatusRepository;
import com.generation.italy.repository.TaskRepository;
import com.generation.italy.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable(value = "id") Long taskId) {
        Task task = taskService.getTaskById(taskId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found for this id :: " + taskId));
        return ResponseEntity.ok().body(task);
    }

    @PostMapping
    public Task createTask(@Valid @RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable(value = "id") Long taskId, @Valid @RequestBody Task taskDetails) {
        Task task = taskService.getTaskById(taskId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found for this id :: " + taskId));
        Task updatedTask = taskService.updateTask(taskId, taskDetails);
        return ResponseEntity.accepted().body(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable(value = "id") Long taskId) {
        Task task = taskService.getTaskById(taskId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found for this id :: " + taskId));
        taskService.deleteTask(taskId);
        return ResponseEntity.ok().<Void>build();
    }
    
    @PostMapping("/changestatus")
    public ResponseEntity<Task> changeStatus(@PathVariable(value="task_id") Long taskId,@PathVariable(value="status_id") Long statusID) {
    	return new ResponseEntity<Task>(taskService.setStatus(taskId, statusID), HttpStatus.ACCEPTED);
    }
    	
}
