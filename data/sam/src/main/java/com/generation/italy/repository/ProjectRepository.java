package com.generation.italy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.italy.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{

}
