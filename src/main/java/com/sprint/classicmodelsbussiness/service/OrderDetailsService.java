package com.sprint.classicmodelsbussiness.service;

import java.util.List;

import com.sprint.classicmodelsbussiness.dto.OrderDetailsDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;

public interface OrderDetailsService {

	List<OrderDetailsDto> getAllOrderDetails();

	ResponseDto saveOrderDetails(OrderDetailsDto orderDetails);

	ResponseDto updateQuantityOrdered(Integer orderedNumber, String productCode, Integer quantity);

	ResponseDto getTotalofOrder(Integer orderedNumber);

	ResponseDto getAllTotal();

	List<OrderDetailsDto> getOrderDetailsByOrderNumber(Integer orderNumber);

	List<OrderDetailsDto> getOrderDetailsByProductCode(String productCode);

	ResponseDto getCountByProductCode(String productCode);

	List<OrderDetailsDto> getMaxOrderDetails();

	Boolean existsByProductAndCustomerId(String productLine, Integer orderNumber);

	ResponseDto updateOrderDetailsById(String productCode, Integer orderNumber, OrderDetailsDto dto);
}
