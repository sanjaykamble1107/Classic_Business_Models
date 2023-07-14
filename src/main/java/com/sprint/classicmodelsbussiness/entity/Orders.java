package com.sprint.classicmodelsbussiness.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "orderNumber", nullable = false)
	private Integer orderNumber;
	
	@Column(name = "orderDate", nullable = false)
	private LocalDate orderDate;
	
	@Column(name = "requiredDate", nullable = false)
	private LocalDate requiredDate;
	
	@Column(name = "shippedDate", nullable = true)
	private LocalDate shippedDate;
	
	@Column(name = "status", nullable = false)
	private String status;
	
	@Column(name = "comments", nullable = true, columnDefinition = "text")
	private String comments;

	@ManyToOne
	@JoinColumn(name = "customerNumber", nullable = false)
	private Customers customer;

	public Orders(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Orders(Integer orderNumber, LocalDate orderDate, LocalDate requiredDate, LocalDate shippedDate,
			String status, String comments, Customers customer) {
		super();
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
		this.shippedDate = shippedDate;
		this.status = status;
		this.comments = comments;
		this.customer = customer;
	}

	public Orders() {
		super();
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDate getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(LocalDate requiredDate) {
		this.requiredDate = requiredDate;
	}

	public LocalDate getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(LocalDate shippedDate) {
		this.shippedDate = shippedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Customers getCustomer() {
		return customer;
	}

	public void setCustomer(Customers customerNumber) {
		this.customer = customerNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(comments, customer, orderDate, orderNumber, requiredDate, shippedDate, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		return Objects.equals(comments, other.comments) && Objects.equals(customer, other.customer)
				&& Objects.equals(orderDate, other.orderDate) && Objects.equals(orderNumber, other.orderNumber)
				&& Objects.equals(requiredDate, other.requiredDate) && Objects.equals(shippedDate, other.shippedDate)
				&& Objects.equals(status, other.status);
	}

	@Override
	public String toString() {
		return "Orders [orderNumber=" + orderNumber + ", orderDate=" + orderDate + ", requiredDate=" + requiredDate
				+ ", shippedDate=" + shippedDate + ", status=" + status + ", comments=" + comments + ", customer="
				+ customer + "]";
	}

}
