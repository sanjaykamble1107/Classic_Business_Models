package com.sprint.classicmodelsbussiness.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "payments")
@IdClass(PaymentsId.class)

public class Payments implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@ManyToOne
	@JoinColumn(name = "customerNumber", nullable = false)
	private Customers customers;
	
	@Id
	@Column(name = "checkNumber", nullable = false)
	private String checkNumber;
	
	@Column(name = "paymentDate", nullable = false)
	private LocalDate paymentDate;
	
	@Column(name = "amount", nullable = false)
	private BigDecimal amount;

	public Payments() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customers getCustomers() {
		return customers;
	}

	public void setCustomers(Customers customers) {
		this.customers = customers;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Payments [customers=" + customers + ", checkNumber=" + checkNumber + ", paymentDate=" + paymentDate
				+ ", amount=" + amount + "]";
	}

}

class PaymentsId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer customers;
	private String checkNumber;

	public PaymentsId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentsId(Integer customers, String checkNumber) {
		super();
		this.customers = customers;
		this.checkNumber = checkNumber;
	}

	public Integer getCustomers() {
		return customers;
	}

	public void setCustomers(Integer customers) {
		this.customers = customers;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	@Override
	public String toString() {
		return "PaymentsId [customers=" + customers + ", checkNumber=" + checkNumber + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(checkNumber, customers);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentsId other = (PaymentsId) obj;
		return Objects.equals(checkNumber, other.checkNumber) && Objects.equals(customers, other.customers);
	}

}