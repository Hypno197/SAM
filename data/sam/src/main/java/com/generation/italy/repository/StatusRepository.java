package com.generation.italy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.italy.model.Status;
import com.generation.italy.model.Task;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long>{

}