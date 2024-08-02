package com.generation.italy.model;

import java.time.LocalDate;

public class Filter {
	
	/* const searchName = document.getElementById("searchName").value
    const searchProjectID = document.getElementById("searchProjectID").value
    const searchUserID = document.getElementById("searchProjectID").value
    const searchStatus = document.getElementById("searchStatus").value
    const searchEndDate = document.getElementById("searchEndDate").value*/
	
	private String searchName;
	private Long projectID, userID, statusID;
	private LocalDate endDate;
	
	
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public Long getProjectID() {
		return projectID;
	}
	public void setProjectID(Long projectID) {
		this.projectID = projectID;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public Long getStatusID() {
		return statusID;
	}
	public void setStatusID(Long statusID) {
		this.statusID = statusID;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

}
