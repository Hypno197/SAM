package com.generation.italy.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.italy.model.Filter;
import com.generation.italy.model.Status;
import com.generation.italy.model.Task;
import com.generation.italy.repository.StatusRepository;
import com.generation.italy.repository.TaskRepository;
import com.generation.italy.repository.UserRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private StatusRepository statusRepository;
	@Autowired
	private UserRepository userRepository;

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

	public List<Task> getTaskByOwnerID(Long id) {
		return taskRepository.findByOwnerID(id);
	}

	public List<Task> getTaskByUserID(Long id) {
		return taskRepository.findByUserID(id);
	}

	public Task updateTask(Long id, Task taskDetails) {
		Task task = taskRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found for this id :: " + id));
		task.setTask_name(taskDetails.getTask_name());
		task.setTask_desc(taskDetails.getTask_desc());
		task.setEnd_date(taskDetails.getEnd_date());
		task.setStatus(taskDetails.getStatus());
		return taskRepository.save(task);
	}

	public Task setStatus(Long id, Long statusID) {
		Task task = taskRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found for this ID: " + id));
		Status status = statusRepository.findById(statusID).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status not found for this ID: " + statusID));
		task.setStatus(status);
		return taskRepository.save(task);

	}

	public Task setCompletion(Long id) {
		Task task = taskRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found for this ID: " + id));
		task.setCompletion_date(LocalDate.now());
		Status status = statusRepository.findByStatus("Completata");
		task.setStatus(status);
		return taskRepository.save(task);
	}

	public Task assignUser(Long id, Long userID) {
		Task task = taskRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found for this ID: " + id));
		task.setUserID(userID);
		return taskRepository.save(task);
	}

	public Task unassignUser(Long id) {
		Task task = taskRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found for this ID: " + id));
		task.setUserID(null);
		task = taskRepository.save(task);
		System.out.println("mario :" + task.toString());
		return task;
	}

	public ResponseEntity<List<Task>> filterTasks(Filter filter) {
		List<Task> taskList = taskRepository.findAll();
		if (filter.getSearchName() != null) {
			taskList = taskList.stream().filter((task) -> task.getTask_name().contains(filter.getSearchName()))
					.collect(Collectors.toList());
		}
		if (filter.getUserID() != null) {
			taskList = taskList.stream().filter((task) -> task.getUserID() == filter.getUserID())
					.collect(Collectors.toList());
		}
		if (filter.getProjectID() != null) {
			taskList = taskList.stream().filter((task) -> task.getProject_id() == filter.getProjectID())
					.collect(Collectors.toList());
		}
		if (filter.getStatusID() != null) {
			taskList = taskList.stream().filter((task) -> task.getStatus().getId() == filter.getStatusID())
					.collect(Collectors.toList());
		}
		if (filter.getEndDate() != null) {
			taskList = taskList.stream().filter((task) -> task.getEnd_date().isBefore(filter.getEndDate()))
					.collect(Collectors.toList());
		}
		System.out.println(taskList.toString());
		if (taskList.size() > 0)
			return ResponseEntity.ok().body(taskList);
		else {
			return ResponseEntity.notFound().build();
		}
	}

}
