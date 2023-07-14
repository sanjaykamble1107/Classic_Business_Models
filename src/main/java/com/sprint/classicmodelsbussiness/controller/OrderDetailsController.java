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

import com.sprint.classicmodelsbussiness.dto.OrderDetailsDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;
import com.sprint.classicmodelsbussiness.service.OrderDetailsService;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api")
public class OrderDetailsController {

	@Autowired
	private OrderDetailsService orderDetailsService;

	@GetMapping("v1/orderdetails/all_order_details")
	public ResponseEntity<List<OrderDetailsDto>> getAllOrderDetails() {
		List<OrderDetailsDto> prodDto = orderDetailsService.getAllOrderDetails();

		return new ResponseEntity<List<OrderDetailsDto>>(prodDto, HttpStatus.OK);
	}

	@PostMapping("v1/orderdetails/")
	public ResponseEntity<ResponseDto> saveOrderDetails(@Valid @RequestBody OrderDetailsDto orderDetails) {
		ResponseDto prodDto = orderDetailsService.saveOrderDetails(orderDetails);

		return new ResponseEntity<ResponseDto>(prodDto, HttpStatus.OK);
	}

	@PutMapping("v1/orderdetails/{order_number}/{product_code}/{quantity_orderd}")
	public ResponseEntity<ResponseDto> updateQuantityOrdered(@Valid @PathVariable Integer order_number,
			@PathVariable String product_code, @PathVariable Integer quantity_orderd) {
		ResponseDto prodDto = orderDetailsService.updateQuantityOrdered(order_number, product_code, quantity_orderd);

		return new ResponseEntity<ResponseDto>(prodDto, HttpStatus.OK);
	}

	@GetMapping("v1/orderdetails/get_total/{order_number}")
	public ResponseEntity<ResponseDto> getTotalofOrder(@PathVariable Integer order_number) {
		ResponseDto prodDto = orderDetailsService.getTotalofOrder(order_number);

		return new ResponseEntity<ResponseDto>(prodDto, HttpStatus.FOUND);
	}

	@GetMapping("v1/orderdetails/total_sale")
	public ResponseEntity<ResponseDto> getTotal() {
		ResponseDto prodDto = orderDetailsService.getAllTotal();

		return new ResponseEntity<ResponseDto>(prodDto, HttpStatus.FOUND);
	}

	@GetMapping("v1/orderdetails/{order_number}")
	public ResponseEntity<List<OrderDetailsDto>> getOrderDetailsByOrderNumber(@PathVariable Integer order_number) {
		List<OrderDetailsDto> prodDto = orderDetailsService.getOrderDetailsByOrderNumber(order_number);

		return new ResponseEntity<List<OrderDetailsDto>>(prodDto, HttpStatus.OK);
	}

	@GetMapping("v1/orderdetails/count/{product_code}")
	public ResponseEntity<ResponseDto> getCountByProductCode(@PathVariable String product_code) {
		ResponseDto prodDto = orderDetailsService.getCountByProductCode(product_code);

		return new ResponseEntity<ResponseDto>(prodDto, HttpStatus.FOUND);
	}

	@GetMapping("v1/orderdetails/max/price")
	public ResponseEntity<List<OrderDetailsDto>> getMaxOrderDetails() {
		List<OrderDetailsDto> prodDto = orderDetailsService.getMaxOrderDetails();

		return new ResponseEntity<List<OrderDetailsDto>>(prodDto, HttpStatus.FOUND);
	}

	@PutMapping("v1/orderdetails/update/{order_number}/{product_code}")
	public ResponseEntity<ResponseDto> updateOrderById(@Valid @PathVariable Integer order_number,
			@PathVariable String product_code, @Valid @RequestBody OrderDetailsDto dto) {
		ResponseDto prodDto = orderDetailsService.updateOrderDetailsById(product_code, order_number, dto);

		return new ResponseEntity<ResponseDto>(prodDto, HttpStatus.OK);
	}

}
