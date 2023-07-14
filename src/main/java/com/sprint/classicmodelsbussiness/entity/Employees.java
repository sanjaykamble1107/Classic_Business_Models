package com.sprint.classicmodelsbussiness.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employees implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3467099571516160135L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "employeeNumber", nullable = false)
	Integer employeeNumber;
	
	@Column(name = "lastName", nullable = false)
	String lastName;
	
	@Column(name = "firstName", nullable = false)
	String firstName;
	
	@Column(name = "extension", nullable = false)
	String extension;
	
	@Column(name = "email", nullable = false)
	String email;

	@ManyToOne
	@JoinColumn(name = "officeCode", nullable = false)
	private Offices office;
	
	@Column(name = "reportsTo", nullable = false)
	Integer reportsTo;
	
	@Column(name = "jobTitle", nullable = false)
	String jobTitle;

	public Employees() {
		// TODO Auto-generated constructor stub
	}

	public Integer getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(Integer employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Offices getOfficeCode() {
		return office;
	}

	public void setOfficeCode(Offices office) {
		this.office = office;
	}

	public Integer getReportsTo() {
		return reportsTo;
	}

	public void setReportsTo(Integer reportsTo) {
		this.reportsTo = reportsTo;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

}
