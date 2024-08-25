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
@Table(name = "milestones")
public class Milestone {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String mile_name, mile_desc, mile_icon;

	private Integer mile_value, mile_total;

	@NotNull
	private Long projectID, owner_id;

	private LocalDate start_date, end_date, completion_date;

	public Integer getMile_value() {
		return mile_value;
	}

	public Integer getMile_total() {
		return mile_total;
	}

	public void setMile_total(Integer mile_total) {
		this.mile_total = mile_total;
	}

	public void setMile_value(Integer mile_value) {
		this.mile_value = mile_value;
	}

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

	public String getMile_icon() {
		return mile_icon;
	}

	public void setMile_icon(String mile_icon) {
		this.mile_icon = mile_icon;
	}

	public Long getProjectID() {
		return projectID;
	}

	public void setProjectID(Long projectID) {
		this.projectID = projectID;
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
