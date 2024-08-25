package com.generation.italy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.generation.italy.model.Milestone;
import com.generation.italy.service.MilestoneService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/milestones")
@CrossOrigin
public class MilestoneController {

    @Autowired
    private MilestoneService milestoneService;

    @GetMapping
    public List<Milestone> getAllMilestones() {
        return milestoneService.getAllMilestones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Milestone> getMilestoneById(@PathVariable(value = "id") Long milestoneId) {
        Milestone milestone = milestoneService.getMilestoneById(milestoneId);
        return ResponseEntity.ok().body(milestone);
    }
    
    @GetMapping("/project/{id}")
    public ResponseEntity<List<Milestone>> getMilestonesByProject(@PathVariable(value="id") Long projectID) {
    	List<Milestone> milestones = milestoneService.findByProject(projectID);
    	if (milestones.size()>0) {
    		return ResponseEntity.ok().body(milestones);
    	}
    	else return ResponseEntity.notFound().build();
    }
    
    
    @PostMapping
    public Milestone createMilestone(@RequestBody Milestone milestone) {
        return milestoneService.createMilestone(milestone);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Milestone> updateMilestone(@PathVariable(value = "id") Long milestoneId, @Valid @RequestBody Milestone milestoneDetails) {
        Milestone milestone = milestoneService.getMilestoneById(milestoneId);
        Milestone updatedMilestone = milestoneService.updateMilestone(milestoneId, milestoneDetails);
        return ResponseEntity.accepted().body(updatedMilestone);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMilestone(@PathVariable(value = "id") Long milestoneId) {
        milestoneService.deleteMilestone(milestoneId);
        return ResponseEntity.accepted().build();
    }
}
