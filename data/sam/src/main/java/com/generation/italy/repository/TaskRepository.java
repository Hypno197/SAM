package com.generation.italy.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.italy.model.Milestone;
import com.generation.italy.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	List<Task> findByOwnerID(Long id);
	List<Task> findByUserID(Long id);
	List<Task> findByMilestone(Milestone milestone);
	List<Task> findByMilestoneId(Long milestoneId);
} 
