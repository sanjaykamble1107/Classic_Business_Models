package com.sprint.classicmodelsbussiness.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orderdetails")
@IdClass(OrderDetailsId.class)
public class OrderDetails implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "orderNumber", nullable = false)
	private Orders order;

	@Id
	@ManyToOne
	@JoinColumn(name = "productCode", nullable = false)
	private Products product;
	
	@Column(name = "quantityOrdered", nullable = false)
	private Integer quantityOrdered;
	
	@Column(name = "priceEach", nullable = false)
	private BigDecimal priceEach;
	
	@Column(name = "orderLineNumber", nullable = false)
	private Short orderLineNumber;

	public OrderDetails() {
		super();
	}

	public OrderDetails(Orders order, Products product, Integer quantityOrdered, BigDecimal priceEach,
			Short orderLineNumber) {
		super();
		this.order = order;
		this.product = product;
		this.quantityOrdered = quantityOrdered;
		this.priceEach = priceEach;
		this.orderLineNumber = orderLineNumber;
//		this.orderNumber=order.getOrderNumber();
//		this.productCode=product.getProductCode();
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}

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
//	public Integer getOrderNumber() {
//		return orderNumber;
//	}
//
//	public void setOrderNumber(Integer orderNumber) {
//		this.orderNumber = orderNumber;
//	}
//
//	public String getProductCode() {
//		return productCode;
//	}
//
//	public void setProductCode(String productCode) {
//		this.productCode = productCode;
//	}

	@Override
	public int hashCode() {
		return Objects.hash(order, orderLineNumber, priceEach, product, quantityOrdered);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetails other = (OrderDetails) obj;
		return Objects.equals(order, other.order) && Objects.equals(orderLineNumber, other.orderLineNumber)
				&& Objects.equals(priceEach, other.priceEach) && Objects.equals(product, other.product)
				&& Objects.equals(quantityOrdered, other.quantityOrdered);
	}

	@Override
	public String toString() {
		return "OrderDetails [order=" + order + ", product=" + product + ", quantityOrdered=" + quantityOrdered
				+ ", priceEach=" + priceEach + ", orderLineNumber=" + orderLineNumber + "]";
	}

}
