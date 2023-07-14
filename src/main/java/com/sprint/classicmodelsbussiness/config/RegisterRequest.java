package com.sprint.classicmodelsbussiness.config;

public class RegisterRequest {

	private String name;
	private String password;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public RegisterRequest(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}
	public RegisterRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "RegisterRequest [name=" + name + ", password=" + password + "]";
	}
	
	
	
	
	

	
}
