package com.generation.italy.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
	
	@NotBlank(message="nome non valido")
		private String project_name;
	
	@NotNull(message ="data progetto non valida")
		private LocalDate start_date, end_date, expected_date, completion_date;
	
	@NotBlank(message="status non valido")
		private String status;
	
	@NotNull(message="budget non valido")
		private Double budget;
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private User user;
}
