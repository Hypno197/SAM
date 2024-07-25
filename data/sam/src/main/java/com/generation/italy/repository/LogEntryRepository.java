package com.generation.italy.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.italy.model.LogEntry;

@Repository
public interface LogEntryRepository extends JpaRepository<LogEntry, Long> {
}
