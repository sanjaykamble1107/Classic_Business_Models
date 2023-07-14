package com.sprint.classicmodelsbussiness.dto;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CustomersDto {

	private Integer customerNumber;

	@NotBlank(message = " CustomerName Should not be null")
	@Size(min = 3, max = 50, message = "Range of characters for customerName is 3 to 50 please enter valid customerName")
	private String customerName;

	@NotBlank(message = " ContactFirstName Should not be null")
	@Size(min = 3, max = 50, message = "Range of characters for contactFirstName is 3 to 50 please enter valid contactFirstName")
	private String contactFirstName;

	@NotBlank(message = "contactLastName Should not be null")
	@Size(min = 3, max = 50, message = "Range of characters for contactLastName is 3 to 50 please enter valid contactLastName")
	private String contactLastName;

	@NotBlank(message = "Please provide the phone Number")
	@Size(min = 10, max = 50, message = "Range of characters for phone is 3 to 50 please enter valid phone")
	private String phone;

	@NotBlank(message = "Please provide the Addres")
	@Size(min = 3, max = 50, message = "Range of characters for addressLine1 is 3 to 50 please enter valid addressLine1")
	private String addressLine1;

	private String addressLine2;

	@NotBlank(message = "Please provide the City")
	@Size(min = 3, max = 50, message = "Range of characters for city is 3 to 50 please enter valid city")
	private String city;

	private String state;

	@Size(min = 3, max = 50, message = "Range of characters for country is 3 to 50 please enter valid country")
	@NotBlank(message = "Please provide the Country")
	private String country;

	@Size(min = 3, max = 15, message = "Range of characters for postalCode is 3 to 50 please enter valid postalCode")
	private String postalCode;

	private Integer salesRepEmployeeNumber;

	@Digits(integer = 10, fraction = 2, message = "Limit of characters for creditLimit is integer=10, fraction=2. please enter valid creditLimit")
	private BigDecimal creditLimit;

	public CustomersDto(Integer customerNumber, String customerName, String contactFirstName, String contactLastName,

			String phone, String addressLine1, String addressLine2, String city, String state, String country,

			String postalCode, Integer salesRepEmployeeNumber, BigDecimal creditLimit) {

		super();

		this.customerNumber = customerNumber;

		this.customerName = customerName;

		this.contactFirstName = contactFirstName;

		this.contactLastName = contactLastName;

		this.phone = phone;

		this.addressLine1 = addressLine1;

		this.addressLine2 = addressLine2;

		this.city = city;

		this.state = state;

		this.country = country;

		this.postalCode = postalCode;

		this.salesRepEmployeeNumber = salesRepEmployeeNumber;

		this.creditLimit = creditLimit;

	}

	public CustomersDto() {

		super();

	}

	public Integer getCustomerNumber() {

		return customerNumber;

	}

	public void setCustomerNumber(Integer customerNumber) {

		this.customerNumber = customerNumber;

	}

	public String getCustomerName() {

		return customerName;

	}

	public void setCustomerName(String customerName) {

		this.customerName = customerName;

	}

	public String getContactFirstName() {

		return contactFirstName;

	}

	public void setContactFirstName(String contactFirstName) {

		this.contactFirstName = contactFirstName;

	}

	public String getContactLastName() {

		return contactLastName;

	}

	public void setContactLastName(String contactLastName) {

		this.contactLastName = contactLastName;

	}

	public String getPhone() {

		return phone;

	}

	public void setPhone(String phone) {

		this.phone = phone;

	}

	public String getAddressLine1() {

		return addressLine1;

	}

	public void setAddressLine1(String addressLine1) {

		this.addressLine1 = addressLine1;

	}

	public String getAddressLine2() {

		return addressLine2;

	}

	public void setAddressLine2(String addressLine2) {

		this.addressLine2 = addressLine2;

	}

	public String getCity() {

		return city;

	}

	public void setCity(String city) {

		this.city = city;

	}

	public String getState() {

		return state;

	}

	public void setState(String state) {

		this.state = state;

	}

	public String getCountry() {

		return country;

	}

	public void setCountry(String country) {

		this.country = country;

	}

	public Integer getSalesRepEmployeeNumber() {

		return salesRepEmployeeNumber;

	}

	public void setSalesRepEmployeeNumber(Integer salesRepEmployeeNumber) {

		this.salesRepEmployeeNumber = salesRepEmployeeNumber;

	}

	public String getPostalCode() {

		return postalCode;

	}

	public void setPostalCode(String postalCode) {

		this.postalCode = postalCode;

	}

	public BigDecimal getCreditLimit() {

		return creditLimit;

	}

	public void setCreditLimit(BigDecimal creditLimit) {

		this.creditLimit = creditLimit;
	}

	@Override

	public boolean equals(Object obj) {

		if (this == obj)

			return true;

		if (obj == null)

			return false;

		if (getClass() != obj.getClass())

			return false;

		CustomersDto other = (CustomersDto) obj;

		return Objects.equals(addressLine1, other.addressLine1) && Objects.equals(addressLine2, other.addressLine2)

				&& Objects.equals(city, other.city) && Objects.equals(contactFirstName, other.contactFirstName)

				&& Objects.equals(contactLastName, other.contactLastName) && Objects.equals(country, other.country)

				&& Objects.equals(creditLimit, other.creditLimit) && Objects.equals(customerName, other.customerName)

				&& Objects.equals(customerNumber, other.customerNumber) && Objects.equals(phone, other.phone)

				&& Objects.equals(postalCode, other.postalCode)

				&& Objects.equals(salesRepEmployeeNumber, other.salesRepEmployeeNumber)

				&& Objects.equals(state, other.state);

	}

}