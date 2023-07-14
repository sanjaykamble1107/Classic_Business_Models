package com.sprint.classicmodelsbussiness.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PaymentsDto {

	@NotNull(message = "Please provide the customerNumber")
	private Integer customerNumber;

	@NotBlank(message = "Please provide the CheckNumber")
	private String checkNumber;

	@Past(message = "The date should be in past")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate paymentDate;

	@NotNull(message = "Please provide the amount")
	@Digits(integer = 10, fraction = 2, message = "Limit of characters for amount is integer=10, fraction=2. please enter valid amount")
	private BigDecimal amount;

	public PaymentsDto(Integer customerNumber, String checkNumber, LocalDate paymentDate, BigDecimal amount) {
		super();
		this.customerNumber = customerNumber;
		this.checkNumber = checkNumber;
		this.paymentDate = paymentDate;
		this.amount = amount;
	}

	public PaymentsDto() {
		super();
	}

	public Integer getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "PaymentsDto [customerNumber=" + customerNumber + ", checkNumber=" + checkNumber + ", paymentDate="
				+ paymentDate + ", amount=" + amount + "]";
	}

//    public Object getPaymentId() {
//        // TODO Auto-generated method stub
//        return null;
//    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentsDto other = (PaymentsDto) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(checkNumber, other.checkNumber)
				&& Objects.equals(customerNumber, other.customerNumber)
				&& Objects.equals(paymentDate, other.paymentDate);
	}

}