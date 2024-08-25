package com.generation.italy.controller;

import java.time.LocalDate;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.italy.exception.UnauthorizedException;
import com.generation.italy.model.Filter;
import com.generation.italy.model.Milestone;
import com.generation.italy.model.Task;
import com.generation.italy.model.User;
import com.generation.italy.service.MilestoneService;
import com.generation.italy.service.TaskService;
import com.generation.italy.service.TokenService;
import com.generation.italy.service.UserService;


@RestController
@RequestMapping("/api/tasks")
@CrossOrigin
public class TaskController {

	@Autowired
	private TaskService taskService;

	@Autowired
	private UserService userService;

	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private MilestoneService milestoneService;
	
	@GetMapping
	public List<Task> getAllTasks() {
		return taskService.getAllTasks();
	}

	@GetMapping("/manager")
	public List<Task> getManagerTasks(@RequestHeader String token) {
		milestoneService.updateValue();
		User user = userService.getUserById(tokenService.findByToken(token).getUser_id())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found for this id"));
		if (user != null && user.getRole().getIsAdmin()) {
			return taskService.getTaskByOwnerID(user.getId());
		} else
			throw new UnauthorizedException();
	}
	@GetMapping("/user")
	public List<Task> getUserTasks(@RequestHeader String token) {
		milestoneService.updateValue();
		User user = userService.getUserById(tokenService.findByToken(token).getUser_id())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found for this id"));
		if (user != null) {
			return taskService.getTaskByUserID(user.getId());
		} else
			throw new UnauthorizedException();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable(value = "id") Long taskId) {
		Task task = taskService.getTaskById(taskId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found for this id :: " + taskId));
		return ResponseEntity.ok().body(task);
	}

	@PostMapping
	public Task createTask(@RequestHeader String token, @RequestBody Task task) {
		User user = userService.getUserById(tokenService.findByToken(token).getUser_id())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found for this id"));
		if (user != null && user.getRole().getIsAdmin()) {
			task.setOwnerID(user.getId());
			task.setStart_date(LocalDate.now());
			task = taskService.createTask(task);
			milestoneService.updateValue();
			return task;
		} else
			throw new UnauthorizedException();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Task> updateTask(@RequestHeader String token,@PathVariable(value = "id") Long taskId,@RequestBody Task taskDetails) {
		User user = userService.getUserById(tokenService.findByToken(token).getUser_id())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found for this id"));
		if (user != null && user.getRole().getIsAdmin()) {
		Task task = taskService.getTaskById(taskId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found for this id :: " + taskId));
		taskDetails.setStatus(task.getStatus());
		Task updatedTask = taskService.updateTask(taskId, taskDetails);
			milestoneService.updateValue();
		return ResponseEntity.accepted().body(updatedTask);
		} else
			throw new UnauthorizedException();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTask(@RequestHeader String token, @PathVariable(value = "id") Long taskId) {
		User user = userService.getUserById(tokenService.findByToken(token).getUser_id())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found for this id"));
		if (user != null && user.getRole().getIsAdmin()) {
		Task task = taskService.getTaskById(taskId).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found for this id :: " + taskId));
		taskService.deleteTask(taskId);
		milestoneService.updateValue();
		return ResponseEntity.ok().<Void>build();
		} else
			throw new UnauthorizedException();
	}

	@PutMapping("/changestatus/{task_id}/{status_id}")
	public ResponseEntity<Task> changeStatus(@PathVariable(value = "task_id") Long taskId,
			@PathVariable(value = "status_id") Long statusID) {
		milestoneService.updateValue();
		return new ResponseEntity<Task>(taskService.setStatus(taskId, statusID), HttpStatus.ACCEPTED);	
	}
	
	@GetMapping("/complete/{id}")
	public ResponseEntity<Task> completeTask(@RequestHeader String token, @PathVariable Long id) {
		User user = userService.getUserById(tokenService.findByToken(token).getUser_id())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found for this id"));
		if (user != null ) {
			milestoneService.updateValue();
			return ResponseEntity.ok().body(taskService.setCompletion(id));			
	}else
		throw new UnauthorizedException();
	}		
	@PutMapping("/assign/{id}/{userID}")
	public ResponseEntity<Task> assignTask(@RequestHeader String token, @PathVariable (value = "id") Long taskID, @PathVariable (value = "userID")Long userID){
		User user = userService.getUserById(tokenService.findByToken(token).getUser_id())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found for this id"));
		if (user != null && user.getRole().getIsAdmin()) {
			return ResponseEntity.ok().body(taskService.assignUser(taskID, userID));
	}else
		throw new UnauthorizedException();
		
		
	}
	
	@GetMapping("/unassign/{taskID}")
	public ResponseEntity<Task> unassignTask(@RequestHeader String token, @PathVariable Long taskID){
		User user = userService.getUserById(tokenService.findByToken(token).getUser_id())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found for this id"));
		if (user != null && user.getRole().getIsAdmin()) {
			System.out.println("task id = "+taskID);
			return ResponseEntity.ok().body(taskService.unassignUser(taskID));
	}else
		throw new UnauthorizedException();
	}
	
	@GetMapping("/selfassign/{taskID}")
	public ResponseEntity<Task> selfassignTask(@RequestHeader String token, @PathVariable Long taskID){
		User user = userService.getUserById(tokenService.findByToken(token).getUser_id())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found for this id"));
		if (user != null) {
			return ResponseEntity.ok().body(taskService.assignUser(taskID, user.getId()));
	}else
		throw new UnauthorizedException();
	}
	
	@GetMapping("/milestone/{mileID}")
	public ResponseEntity<List<Task>> findTaskByMilestone(@RequestHeader String token, @PathVariable Long mileID){
		User user = userService.getUserById(tokenService.findByToken(token).getUser_id())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found for this id"));
		if (user != null) {
			return 			taskService.findTasksByMilestoneID(mileID);
	}else
		throw new UnauthorizedException();
	}
	
	@PostMapping("/filter")
	public ResponseEntity<List<Task>> filterTasks(@RequestHeader String token, @RequestBody Filter filter){
		User user = userService.getUserById(tokenService.findByToken(token).getUser_id())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found for this id"));
		if (user != null && user.getRole().getIsAdmin()) {
			return taskService.filterTasks(filter);
	}else
		throw new UnauthorizedException();
		
	}
	
	
	
	
}
