package com.sprint.classicmodelsbussiness.config;

public class JwtResponse {

	private String jwtToken;

	private String username;
	
	private String role;

	
	
	
	
	public String getrole() {
		return role;
	}



	public void setrole(String role) {
		this.role = role;
	}



	public JwtResponse() {
		super();
	}
	
	

	public JwtResponse(String jwtToken, String username) {
		super();
		this.jwtToken = jwtToken;
		this.username = username;
	}

	public JwtResponse(String jwtToken) {
		super();
		this.jwtToken = jwtToken;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "JwtResponse [jwtToken=" + jwtToken + ", username=" + username + "]";
	}
	
	

	

}
