package com.sprint.classicmodelsbussiness.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.classicmodelsbussiness.dto.OrderDto;
import com.sprint.classicmodelsbussiness.dto.ProductDto;
import com.sprint.classicmodelsbussiness.dto.ProductProductLinesDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;
import com.sprint.classicmodelsbussiness.service.OrderService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("v1/orders/")
	public ResponseEntity<ResponseDto> saveOrder(@Valid @RequestBody OrderDto orderDto) {
		ResponseDto prodDto = orderService.saveOrder(orderDto);

		return new ResponseEntity<ResponseDto>(prodDto, HttpStatus.OK);
	}

	@GetMapping("v1/orders/all_orders")
	public ResponseEntity<List<OrderDto>> getAllOrders() {
		List<OrderDto> prodDto = orderService.getAllOrders();

		return new ResponseEntity<List<OrderDto>>(prodDto, HttpStatus.OK);
	}

	@PutMapping("v1/orders/{order_number}/shipped_date/{shipped_date}")
	public ResponseEntity<ResponseDto> updateShippedDate(@Valid @PathVariable Integer order_number,
			@PathVariable String shipped_date) {
		ResponseDto prodDto = orderService.updateShippedDate(order_number, shipped_date);

		return new ResponseEntity<ResponseDto>(prodDto, HttpStatus.OK);
	}

	@PutMapping("v1/orders/{order_number}/status/{status}")
	public ResponseEntity<ResponseDto> updateOrderStatus(@Valid @PathVariable Integer order_number,
			@PathVariable String status) {
		ResponseDto prodDto = orderService.updateOrderStatus(order_number, status);

		return new ResponseEntity<ResponseDto>(prodDto, HttpStatus.OK);
	}

	@GetMapping("v1/orders/customer_number/{customer_number}")
	public ResponseEntity<List<OrderDto>> getOrdersByCustomerNumber(@PathVariable Integer customer_number) {
		List<OrderDto> prodDto = orderService.getOrdersByCustomerNumber(customer_number);

		return new ResponseEntity<List<OrderDto>>(prodDto, HttpStatus.FOUND);
	}

	@GetMapping("v1/orders/order_number/{order_number}")
	public ResponseEntity<OrderDto> getOrdersByOrderNumber(@PathVariable Integer order_number) {
		OrderDto prodDto = orderService.getOrdersByOrderNumber(order_number);

		return new ResponseEntity<OrderDto>(prodDto, HttpStatus.OK);
	}

	@GetMapping("v1/orders/order_date/{order_date}")
	public ResponseEntity<List<OrderDto>> getOrdersByOrderDate(@PathVariable String order_date) {
		List<OrderDto> prodDto = orderService.getOrdersByOrderDate(order_date);

		return new ResponseEntity<List<OrderDto>>(prodDto, HttpStatus.FOUND);
	}

	@GetMapping("v1/orders/required_date/{required_date}")
	public ResponseEntity<List<OrderDto>> getOrdersByRequiredDate(@PathVariable String required_date) {
		List<OrderDto> prodDto = orderService.getOrdersByRequiredDate(required_date);

		return new ResponseEntity<List<OrderDto>>(prodDto, HttpStatus.FOUND);
	}

	@GetMapping("v1/orders/shipped_date/{shipped_date}")
	public ResponseEntity<List<OrderDto>> getOrdersByShippedDate(@PathVariable String shipped_date) {
		List<OrderDto> prodDto = orderService.getOrdersByShippedDate(shipped_date);

		return new ResponseEntity<List<OrderDto>>(prodDto, HttpStatus.FOUND);
	}

	@GetMapping("v1/orders/status/{status}")
	public ResponseEntity<List<OrderDto>> getOrdersByOrderStatus(@PathVariable String status) {
		List<OrderDto> prodDto = orderService.getOrdersByOrderStatus(status);

		return new ResponseEntity<List<OrderDto>>(prodDto, HttpStatus.FOUND);
	}

	@GetMapping("v1/orders/{customer_number}/get/{status}")
	public ResponseEntity<List<OrderDto>> getOrdersByOrderStatusForCustomer(@PathVariable Integer customer_number,
			@PathVariable String status) {
		List<OrderDto> prodDto = orderService.getOrdersByOrderStatusForCustomer(customer_number, status);

		return new ResponseEntity<List<OrderDto>>(prodDto, HttpStatus.FOUND);
	}

	@GetMapping("v1/orders/{order_id}/products")
	public ResponseEntity<List<ProductDto>> getProductsByOrderNumber(@PathVariable Integer order_id) {
		List<ProductDto> prodDto = orderService.getProductsByOrderNumber(order_id);

		return new ResponseEntity<List<ProductDto>>(prodDto, HttpStatus.FOUND);
	}

	@GetMapping("v1/orders/{order_id}/product_names")
	public ResponseEntity<List<String>> getProductsNameByOrderNumber(@PathVariable Integer order_id) {
		List<String> prodDto = orderService.getProductsNameByOrderNumber(order_id);

		return new ResponseEntity<List<String>>(prodDto, HttpStatus.FOUND);
	}

	@GetMapping("v1/order/products")
	public ResponseEntity<List<ProductDto>> getAllProducts() {
		List<ProductDto> prodDto = orderService.getAllProducts();

		return new ResponseEntity<List<ProductDto>>(prodDto, HttpStatus.FOUND);
	}

	@GetMapping("v1/orders/{order_status}/orders")
	public ResponseEntity<List<OrderDto>> getOrdersByDelivered(@PathVariable String order_status) {
		List<OrderDto> prodDto = orderService.getOrdersByDelivered(order_status);

		return new ResponseEntity<List<OrderDto>>(prodDto, HttpStatus.FOUND);
	}

	@GetMapping("v1/orders/product_and_product_line_details")
	public ResponseEntity<List<List<ProductProductLinesDto>>> getProductsForShippedDate() {
		List<List<ProductProductLinesDto>> prodDto = orderService.getProductsForShippedDate();

		return new ResponseEntity<List<List<ProductProductLinesDto>>>(prodDto, HttpStatus.FOUND);
	}

	@PutMapping("v1/orders/update/{order_number}")
	public ResponseEntity<ResponseDto> updateOrderById(@Valid @PathVariable Integer order_number,
			@Valid @RequestBody OrderDto dto) {
		ResponseDto prodDto = orderService.updateOrderById(order_number, dto);

		return new ResponseEntity<ResponseDto>(prodDto, HttpStatus.OK);
	}
}
