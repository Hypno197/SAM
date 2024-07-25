package com.generation.italy.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="minitasks")
public class Minitask {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		@NotBlank
		private String minitask_name, minitask_desc, minitask_status;
		
		@NotNull
		private Long user_id;
		
		private LocalDate start_date, end_date, completion_date;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getMinitask_name() {
			return minitask_name;
		}

		public void setMinitask_name(String minitask_name) {
			this.minitask_name = minitask_name;
		}

		public String getMinitask_desc() {
			return minitask_desc;
		}

		public void setMinitask_desc(String minitask_desc) {
			this.minitask_desc = minitask_desc;
		}

		public String getMinitask_status() {
			return minitask_status;
		}

		public void setMinitask_status(String minitask_status) {
			this.minitask_status = minitask_status;
		}

		public Long getUser_id() {
			return user_id;
		}

		public void setUser_id(Long user_id) {
			this.user_id = user_id;
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

		public LocalDate getCompletion_date() {
			return completion_date;
		}

		public void setCompletion_date(LocalDate completion_date) {
			this.completion_date = completion_date;
		}
		
}
