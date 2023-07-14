package com.sprint.classicmodelsbussiness.dto;

import java.math.BigDecimal;
import java.util.Objects;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OrderDetailsDto {

	@NotNull(message = "orderNumber Should not be null")
	private Integer orderNumber;

	@NotBlank(message = "productCode Should not be null")
	@Size(min = 1, max = 15, message = "Range of characters for productCode is 1 to 15. please enter valid productCode")
	private String productCode;

	@NotNull(message = "quantityOrdered Should not be null")
	private Integer quantityOrdered;

	@NotNull(message = "priceEach Should not be null")
	@Digits(integer = 10, fraction = 2, message = "Limit of characters for priceEach is integer=10, fraction=2. please enter valid priceEach")
	private BigDecimal priceEach;

	@NotNull(message = "orderLineNumber Should not be null")
	private Short orderLineNumber;

	public OrderDetailsDto() {
		super();
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

//	public void setOrder(Orders order) {
//		this.order = order;
//	}
//	Orders order, Products product, 
	public OrderDetailsDto(Integer orderNumber, String productCode, Integer quantityOrdered, BigDecimal priceEach,
			Short orderLineNumber) {
		super();
//		this.order = order;
//		this.product = product;
		this.quantityOrdered = quantityOrdered;
		this.priceEach = priceEach;
		this.orderLineNumber = orderLineNumber;
		this.orderNumber = orderNumber;
		this.productCode = productCode;
	}

//	public Orders getOrder() {
//		return order;
//	}
//	public void setOrde(Orders order) {
//		this.order = order;
//	}
//	public Products getProduct() {
//		return product;
//	}
//	public void setProduct(Products product) {
//		this.product = product;
//	}
	public Integer getQuantityOrdered() {
		return quantityOrdered;
	}

	public void setQuantityOrdered(Integer quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	public BigDecimal getPriceEach() {
		return priceEach;
	}

	public void setPriceEach(BigDecimal priceEach) {
		this.priceEach = priceEach;
	}

	public Short getOrderLineNumber() {
		return orderLineNumber;
	}

	public void setOrderLineNumber(Short orderLineNumber) {
		this.orderLineNumber = orderLineNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderLineNumber, orderNumber, priceEach, productCode, quantityOrdered);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetailsDto other = (OrderDetailsDto) obj;
		return Objects.equals(orderLineNumber, other.orderLineNumber) && Objects.equals(orderNumber, other.orderNumber)
				&& Objects.equals(priceEach, other.priceEach) && Objects.equals(productCode, other.productCode)
				&& Objects.equals(quantityOrdered, other.quantityOrdered);
	}

}
