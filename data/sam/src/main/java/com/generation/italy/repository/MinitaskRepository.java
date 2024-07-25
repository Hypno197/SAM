package com.generation.italy.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.italy.model.Minitask;

@Repository
public interface MinitaskRepository extends JpaRepository<Minitask, Long> {
}
