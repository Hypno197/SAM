package com.generation.italy.model;

public class AuthResponse {
    private String token;
    private Role role;
    private Boolean isAdmin;
    private String sessID;
    
    public AuthResponse(String token, Role role) {
        this.token = token;
        this.role = role;
        this.isAdmin =role.getIsAdmin();
    }

    public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
