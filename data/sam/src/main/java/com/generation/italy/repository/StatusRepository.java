package com.generation.italy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.italy.model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long>{
	Status findByStatus (String status);
}
