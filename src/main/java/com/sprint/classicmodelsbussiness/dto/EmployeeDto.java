package com.sprint.classicmodelsbussiness.dto;

import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeDto {

	private Integer employeeNumber;

	@NotBlank(message = "lastName Should not be null")
	@Size(min = 3, max = 50, message = "Range of characters for lastName is 3 to 50. please enter valid lastName")
	private String lastName;

	@NotBlank(message = "firstName Should not be null")
	@Size(min = 3, max = 50, message = "Range of characters for firstName is 3 to 50. please enter valid firstName")
	private String firstName;

	@NotBlank(message = "extension Should not be null")
	@Size(min = 3, max = 10, message = "Range of characters for extension is 3 to 10. please enter valid extension")
	private String extension;

	@Email
	@NotBlank(message = "email Should not be null")
	@Size(min = 6, max = 100, message = "Range of characters for email is 3 to 100. please enter valid email")
	private String email;

	@NotNull(message = "officeCode Should not be null")
	private Integer officeCode;

	private Integer reportsTo;

	@NotBlank(message = "jobTitle Should not be null")
	@Size(min = 2, max = 50, message = "Range of characters for jobTitle is 2 to 50. please enter valid jobTitle")
	private String jobTitle;

	public EmployeeDto(Integer employeeNumber, String lastName, String firstName, String extension, String email,
			Integer officeCode, Integer reportsTo, String jobTitle) {
		super();
		this.employeeNumber = employeeNumber;
		this.lastName = lastName;
		this.firstName = firstName;
		this.extension = extension;
		this.email = email;
		this.officeCode = officeCode;
		this.reportsTo = reportsTo;
		this.jobTitle = jobTitle;
	}

	public EmployeeDto() {

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

	public Integer getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(Integer officeCode) {
		this.officeCode = officeCode;
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

	@Override
	public int hashCode() {
		return Objects.hash(email, employeeNumber, extension, firstName, jobTitle, lastName, officeCode, reportsTo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeDto other = (EmployeeDto) obj;
		return Objects.equals(email, other.email) && Objects.equals(employeeNumber, other.employeeNumber)
				&& Objects.equals(extension, other.extension) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(jobTitle, other.jobTitle) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(officeCode, other.officeCode) && Objects.equals(reportsTo, other.reportsTo);
	}

}
