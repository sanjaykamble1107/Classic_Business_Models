package com.sprint.classicmodelsbussiness.dto;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class OfficeDto {

	private Integer officeCode;

	@NotBlank(message = "city Should not be null")
	@Size(min = 3, max = 50, message = "Range of characters for city is 3 to 50. please enter valid city")
	private String city;

	@NotBlank(message = "phone Should not be null")
	@Size(min = 3, max = 50, message = "Range of characters for phone is 3 to 50. please enter valid phone")
	private String phone;

	@NotBlank(message = "addressLine1 Should not be null")
	@Size(min = 3, max = 50, message = "Range of characters for addressLine1 is 3 to 50. please enter valid addressLine1")
	private String addressLine1;

	@Size(min = 3, max = 50, message = "Range of characters for addressLine2 is 3 to 50. please enter valid addressLine2")
	private String addressLine2;

	@Size(min = 2, max = 50, message = "Range of characters for state is 3 to 50. please enter valid state")
	private String state;

	@NotBlank(message = "country Should not be null")
	@Size(min = 2, max = 50, message = "Range of characters for country is 3 to 50. please enter valid country")
	private String country;

	@NotBlank(message = "postalCode Should not be null")
	@Size(min = 3, max = 15, message = "Range of characters for postalCode is 3 to 15. please enter valid postalCode")
	private String postalCode;

	@NotBlank(message = "territory Should not be null")
	@Size(min = 2, max = 10, message = "Range of characters for territory is 2 to 10. please enter valid territory")
	private String territory;

	public OfficeDto(Integer officeCode, String city, String phone, String addressLine1, String addressLine2,
			String state, String country, String postalCode, String territory) {
		super();
		this.officeCode = officeCode;
		this.city = city;
		this.phone = phone;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.state = state;
		this.country = country;
		this.postalCode = postalCode;
		this.territory = territory;
	}

	public OfficeDto() {
		super();
	}

	public Integer getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(Integer officeCode) {
		this.officeCode = officeCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getTerritory() {
		return territory;
	}

	public void setTerritory(String territory) {
		this.territory = territory;
	}

	@Override
	public int hashCode() {
		return Objects.hash(addressLine1, addressLine2, city, country, officeCode, phone, postalCode, state, territory);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OfficeDto other = (OfficeDto) obj;
		return Objects.equals(addressLine1, other.addressLine1) && Objects.equals(addressLine2, other.addressLine2)
				&& Objects.equals(city, other.city) && Objects.equals(country, other.country)
				&& Objects.equals(officeCode, other.officeCode) && Objects.equals(phone, other.phone)
				&& Objects.equals(postalCode, other.postalCode) && Objects.equals(state, other.state)
				&& Objects.equals(territory, other.territory);
	}

}
