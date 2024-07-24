package com.generation.italy.model;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LogEntry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long user_id, milestone_id, task_id, minitask_id;
	private String message, logType, logAction;
	private Timestamp time;

	
	public Long getId() {
		return id;
	}
	
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public Timestamp getTime() {
		return time;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public Long getMilestone_id() {
		return milestone_id;
	}
	public void setMilestone_id(Long milestone_id) {
		this.milestone_id = milestone_id;
	}
	public Long getTask_id() {
		return task_id;
	}
	public void setTask_id(Long task_id) {
		this.task_id = task_id;
	}
	public Long getMinitask_id() {
		return minitask_id;
	}
	public void setMinitask_id(Long minitask_id) {
		this.minitask_id = minitask_id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public String getLogAction() {
		return logAction;
	}
	public void setLogAction(String logAction) {
		this.logAction = logAction;
	}
	
}
