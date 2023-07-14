package com.sprint.classicmodelsbussiness.service;

import java.util.List;

import com.sprint.classicmodelsbussiness.dto.OrderDto;
import com.sprint.classicmodelsbussiness.dto.ProductDto;
import com.sprint.classicmodelsbussiness.dto.ProductProductLinesDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;

public interface OrderService {

	List<OrderDto> getAllOrders();

	ResponseDto saveOrder(OrderDto orderDto);

	ResponseDto updateShippedDate(Integer orderNumber, String shippedDate);

	ResponseDto updateOrderStatus(Integer orderNumber, String status);

	List<OrderDto> getOrdersByCustomerNumber(Integer customerNumber);

	OrderDto getOrdersByOrderNumber(Integer orderNumber);

	List<OrderDto> getOrdersByOrderDate(String orderDate);

	List<OrderDto> getOrdersByRequiredDate(String requiredDate);

	List<OrderDto> getOrdersByShippedDate(String shippedDate);

	List<OrderDto> getOrdersByOrderStatus(String status);

	List<OrderDto> getOrdersByOrderStatusForCustomer(Integer customerNumber, String status);

	List<ProductDto> getProductsByOrderNumber(Integer orderNumber);

	List<String> getProductsNameByOrderNumber(Integer orderNumber);

	List<ProductDto> getAllProducts();

	List<OrderDto> getOrdersByDelivered(String status);

	List<List<ProductProductLinesDto>> getProductsForShippedDate();

	Boolean existsByOrderNumber(Integer orderNumber);

	ResponseDto updateOrderById(Integer orderNumber, OrderDto dto);

}
