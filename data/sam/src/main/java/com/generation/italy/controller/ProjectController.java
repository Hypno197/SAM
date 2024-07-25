package com.generation.italy.controller;

import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.italy.model.Project;
import com.generation.italy.model.User;
import com.generation.italy.service.ProjectService;
import com.generation.italy.service.UserService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/projects")
public class ProjectController {
	
@Autowired
private ProjectService projectService;
@Autowired
private UserService userService;

@GetMapping
public List<Project> getAllProjects() {
    return projectService.getAllProjects();
}

@GetMapping("/{id}")
public ResponseEntity<Project> getProjectById(@PathVariable(value = "id") Long projectId) {
    Project project = projectService.getProjectById(projectId);
    return new ResponseEntity<Project>(project, HttpStatus.FOUND);
}

@PostMapping
public ResponseEntity<Project> createProject(@Valid @RequestBody Project project) {
	Project createdProject = projectService.createProject(project);
    return new ResponseEntity<Project>(createdProject, HttpStatus.CREATED);
}

@PutMapping("/{id}")
public ResponseEntity<Project> updateProject(@PathVariable(value = "id") Long projectId, @Valid @RequestBody Project projectDetails) {
    Project updatedProject = projectService.updateProject(projectId, projectDetails);
    return new ResponseEntity<Project>(updatedProject, HttpStatus.FOUND);
}

@DeleteMapping("/{id}")
public ResponseEntity<String> deleteProject(@PathVariable(value = "id") Long projectId) {
    projectService.deleteProject(projectId);
    return new ResponseEntity<String>("Progetto numero "+projectId+" eliminato con successo", HttpStatus.ACCEPTED);
}

@PutMapping("/adduser")
public ResponseEntity<Project> addUserToProject(@RequestParam("user_id") Long user_id, @RequestParam("project_id")Long project_id){
	User user = userService.getUserById(user_id).orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found for this id :" + user_id));
	Project project = projectService.getProjectById(project_id);
	Set<User> userList = project.getProjectUsers();
	userList.add(user);
	project.setProjectUsers(userList);
	return new ResponseEntity<Project>((projectService.updateProject(project_id, project)), HttpStatus.ACCEPTED);
}


}