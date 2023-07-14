package com.sprint.classicmodelsbussiness.config;

public class JwtRequest {

	private String name;
	private String password;

	public JwtRequest() {
		super();
	}

	public JwtRequest(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "JwtRequest [name=" + name + ", password=" + password + "]";
	}

}
