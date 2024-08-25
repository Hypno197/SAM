package com.generation.italy.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.italy.model.Milestone;

@Repository
public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
	List<Milestone> findByProjectID(Long id);
}
