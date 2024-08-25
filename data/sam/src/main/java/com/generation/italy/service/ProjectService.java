package com.generation.italy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.italy.model.Milestone;
import com.generation.italy.model.Project;
import com.generation.italy.model.Task;
import com.generation.italy.repository.MilestoneRepository;
import com.generation.italy.repository.ProjectRepository;
import com.generation.italy.repository.TaskRepository;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private MilestoneRepository milestoneRepository;
    @Autowired
    private TaskRepository taskRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found for this id :: " + id));
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found for this id :: " + id));
        List<Milestone> mileList = milestoneRepository.findByProjectID(id);
        for(Milestone m : mileList) {
        	List<Task> taskList = taskRepository.findByMilestone(m);
        		for(Task t : taskList)
        		{
        			taskRepository.delete(t);
        		}
        	milestoneRepository.delete(m);
        }
        
        projectRepository.delete(project);
    }

    public Project updateProject(Long id, Project projectDetails) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found for this id :: " + id));
        project.setProject_name(projectDetails.getProject_name());
        project.setStart_date(projectDetails.getStart_date());
        project.setEnd_date(projectDetails.getEnd_date());
        project.setExpected_date(projectDetails.getExpected_date());
        project.setCompletion_date(projectDetails.getCompletion_date());
        project.setStatus(projectDetails.getStatus());
        project.setBudget(projectDetails.getBudget());
        project.setCustomer(projectDetails.getCustomer());
        project.setUser(projectDetails.getUser());
        project.setProjectUsers(projectDetails.getProjectUsers());
        return projectRepository.save(project);
    }
}

