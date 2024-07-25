package com.generation.italy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.italy.model.Status;
import com.generation.italy.model.Task;
import com.generation.italy.repository.StatusRepository;
import com.generation.italy.repository.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private StatusRepository statusRepository;
    
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found for this id :: " + id));
        task.setTask_name(taskDetails.getTask_name());
        task.setTask_desc(taskDetails.getTask_desc());
        task.setProject_id(taskDetails.getProject_id());
        task.setOwner_id(taskDetails.getOwner_id());
        task.setUser_id(taskDetails.getUser_id());
        task.setStart_date(taskDetails.getStart_date());
        task.setEnd_date(taskDetails.getEnd_date());
        task.setCompletion_date(taskDetails.getCompletion_date());
        task.setStatus(taskDetails.getStatus());
        return taskRepository.save(task);
    }
    
    public Task setStatus(Long id, Long statusID) {
    	Task task = taskRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found for this ID: " + id) );
    	Status status = statusRepository.findById(statusID).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status not found for this ID: "+ statusID));    	
    	task.setStatus(status);
    	return taskRepository.save(task);
    	

    }
}
