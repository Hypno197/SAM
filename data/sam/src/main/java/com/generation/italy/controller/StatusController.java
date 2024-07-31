package com.generation.italy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.italy.model.Status;
import com.generation.italy.repository.StatusRepository;


@RestController
@RequestMapping("/api/status")
@CrossOrigin
public class StatusController {
	
	@Autowired
	private StatusRepository statusRepository;
	
	@GetMapping
	public List<Status> getAllStatus(){
		return statusRepository.findAll();
	}
}
