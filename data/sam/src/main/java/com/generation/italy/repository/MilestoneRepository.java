package com.generation.italy.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.italy.model.Milestone;

@Repository
public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
}
