package com.generation.italy.model;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="projects")
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
	
	@NotBlank(message="nome non valido")
	private String project_name;
		
	private LocalDate start_date, end_date, expected_date, completion_date;
	
	@NotBlank(message="status non valido")
		private String status;
	
	@NotNull(message="budget non valido")
		private Double budget;
	
	@ManyToOne
	@JoinColumn(name= "customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private User user;
	
	@ManyToMany
	@JoinTable(name="project_users",
	joinColumns = @JoinColumn(name="project_id"),
	inverseJoinColumns = @JoinColumn(name ="user_id"))
	private Set<User> projectUsers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
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

	public LocalDate getExpected_date() {
		return expected_date;
	}

	public void setExpected_date(LocalDate expected_date) {
		this.expected_date = expected_date;
	}

	public LocalDate getCompletion_date() {
		return completion_date;
	}

	public void setCompletion_date(LocalDate completion_date) {
		this.completion_date = completion_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<User> getProjectUsers() {
		return projectUsers;
	}

	public void setProjectUsers(Set<User> projectUsers) {
		this.projectUsers = projectUsers;
	}
	
}
