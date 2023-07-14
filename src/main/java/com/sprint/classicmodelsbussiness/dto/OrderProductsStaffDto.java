package com.sprint.classicmodelsbussiness.dto;

import java.util.List;

public class OrderProductsStaffDto {

	OrderDto order;
	List<ProductDto> productList;
	EmployeeDto employee;

	public OrderDto getOrder() {
		return order;
	}

	public void setOrder(OrderDto order) {
		this.order = order;
	}

	public List<ProductDto> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductDto> productList) {
		this.productList = productList;
	}

	public EmployeeDto getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDto employee) {
		this.employee = employee;
	}

	public OrderProductsStaffDto(OrderDto order, List<ProductDto> productList, EmployeeDto employee) {
		super();
		this.order = order;
		this.productList = productList;
		this.employee = employee;
	}

	public OrderProductsStaffDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "OrderProductsStaffDto [order=" + order + ", productList=" + productList + ", employee=" + employee
				+ "]";
	}

}
