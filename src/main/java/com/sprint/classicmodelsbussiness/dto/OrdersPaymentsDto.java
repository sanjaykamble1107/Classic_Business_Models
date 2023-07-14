package com.sprint.classicmodelsbussiness.dto;

import java.util.List;

public class OrdersPaymentsDto {

	List<OrderDto> orderList;
	List<PaymentsDto> paymentList;

	public List<OrderDto> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderDto> orderList) {
		this.orderList = orderList;
	}

	public List<PaymentsDto> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<PaymentsDto> paymentList) {
		this.paymentList = paymentList;
	}

	public OrdersPaymentsDto(List<OrderDto> orderList, List<PaymentsDto> paymentList) {
		super();
		this.orderList = orderList;
		this.paymentList = paymentList;
	}

	public OrdersPaymentsDto() {
		super();

	}

}
