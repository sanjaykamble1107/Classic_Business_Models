package com.sprint.classicmodelsbussiness.entity;

import java.io.Serializable;
import java.util.Objects;

//@Embeddable
public class OrderDetailsId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer order;
	
	private String product;

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public OrderDetailsId(Integer order, String product) {
		super();
		this.order = order;
		this.product = product;
	}

	public OrderDetailsId() {
		super();

	}

	@Override
	public int hashCode() {
		return Objects.hash(order, product);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetailsId other = (OrderDetailsId) obj;
		return Objects.equals(order, other.order) && Objects.equals(product, other.product);
	}

	@Override
	public String toString() {
		return "OrderDetailsId [order=" + order + ", product=" + product + "]";
	}

}
