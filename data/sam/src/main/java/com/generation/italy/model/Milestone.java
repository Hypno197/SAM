package com.generation.italy.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Milestone {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		@NotBlank
		private String mile_name, mile_desc, mile_status;
		
		@NotNull
		private Long project_id, owner_id;
		
		private LocalDate start_date, end_date, completion_date;

		public LocalDate getCompletion_date() {
			return completion_date;
		}

		public void setCompletion_date(LocalDate completion_date) {
			this.completion_date = completion_date;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getMile_name() {
			return mile_name;
		}

		public void setMile_name(String mile_name) {
			this.mile_name = mile_name;
		}

		public String getMile_desc() {
			return mile_desc;
		}

		public void setMile_desc(String mile_desc) {
			this.mile_desc = mile_desc;
		}

		public String getMile_status() {
			return mile_status;
		}

		public void setMile_status(String mile_status) {
			this.mile_status = mile_status;
		}

		public Long getProject_id() {
			return project_id;
		}

		public void setProject_id(Long project_id) {
			this.project_id = project_id;
		}

		public Long getOwner_id() {
			return owner_id;
		}

		public void setOwner_id(Long owner_id) {
			this.owner_id = owner_id;
		}

		public LocalDate getStart_date() {
			return start_date;
		}

		public void setStart_date(LocalDate start_date) {
			this.start_date = start_date;
		}

		public LocalDate getEnd_date() {
			return end_date;
		}

		public void setEnd_date(LocalDate end_date) {
			this.end_date = end_date;
		}
		
}
