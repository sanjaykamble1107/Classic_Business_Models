package com.sprint.classicmodelsbussiness.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.classicmodelsbussiness.dto.OrderDetailsDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;
import com.sprint.classicmodelsbussiness.entity.OrderDetails;
import com.sprint.classicmodelsbussiness.entity.OrderDetailsId;
//import com.sprint.classicmodelsbussiness.entity.OrderDetails.OrderDetailsId;
import com.sprint.classicmodelsbussiness.entity.Orders;
import com.sprint.classicmodelsbussiness.entity.Products;
import com.sprint.classicmodelsbussiness.exception.InsufficientStockException;
import com.sprint.classicmodelsbussiness.exception.OrderDetailsNotFoundException;
import com.sprint.classicmodelsbussiness.repository.OrderDetailsRepository;
import com.sprint.classicmodelsbussiness.repository.OrderRepository;
import com.sprint.classicmodelsbussiness.repository.ProductRepository;
import com.sprint.classicmodelsbussiness.service.OrderDetailsService;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

	@Autowired
	private OrderDetailsRepository repository;

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ProductRepository productRepository;

	public OrderDetailsServiceImpl(OrderDetailsRepository repository) {
		super();
		this.repository = repository;
	}

	public OrderDetailsServiceImpl() {
		super();
	}

	private ResponseDto responseDto = new ResponseDto();

	@Override
	public ResponseDto saveOrderDetails(OrderDetailsDto orderDetails) throws InsufficientStockException {
		if (existsByProductAndCustomerId(orderDetails.getProductCode(), orderDetails.getOrderNumber())) {
			throw new OrderDetailsNotFoundException("OrderDetails with ProductCode and OrderNumber is already exist");
		}
		Optional<Products> product = productRepository.findById(orderDetails.getProductCode());
		if (orderDetails.getQuantityOrdered() > product.get().getQuantityInStock()) {
			throw new InsufficientStockException("Quantity in stock is lesser than quantity ordered");
		}
		try {
			Optional<Orders> order = orderRepository.findById(orderDetails.getOrderNumber());
			OrderDetails prod = new OrderDetails();
			prod.setOrder(order.get());
			prod.setProduct(product.get());

			BeanUtils.copyProperties(orderDetails, prod);
			repository.save(prod);
			responseDto.setMessage("Record Created Successfully");
			return responseDto;
		} catch (Exception e) {
			throw new OrderDetailsNotFoundException("Sorry cannot add OrderDetails");
		}
	}

	@Override
	public List<OrderDetailsDto> getAllOrderDetails() {
		List<OrderDetails> findAll = repository.findAll();
		List<OrderDetailsDto> dtos = new ArrayList<>();
		for (OrderDetails ofc : findAll) {
			OrderDetailsDto dto = new OrderDetailsDto();
			dto.setOrderNumber(ofc.getOrder().getOrderNumber());
			dto.setProductCode(ofc.getProduct().getProductCode());
			BeanUtils.copyProperties(ofc, dto);
			dtos.add(dto);
		}

		return dtos;
	}

	@Override
	public ResponseDto updateQuantityOrdered(Integer orderedNumber, String productCode, Integer quantity)
			throws OrderDetailsNotFoundException {

		OrderDetailsId orderDetId = new OrderDetailsId(orderedNumber, productCode);
		Optional<OrderDetails> orderDetail = repository.findById(orderDetId);
		if (orderDetail.isPresent()) {
			OrderDetails orderDet = orderDetail.get();
			orderDet.setQuantityOrdered(quantity);
			OrderDetailsDto dto = new OrderDetailsDto();
			dto.setOrderNumber(orderDet.getOrder().getOrderNumber());
			dto.setProductCode(orderDet.getProduct().getProductCode());
			BeanUtils.copyProperties(orderDet, dto);
			repository.save(orderDet);
			responseDto.setMessage("Product Quantity updated successfully");
			return responseDto;
//	}
		}
		throw new OrderDetailsNotFoundException("Order details not found");
	}

	@Override
	public ResponseDto getTotalofOrder(Integer orderedNumber) throws OrderDetailsNotFoundException {
		try {
			Orders order = getOrderbyOrderNumber(orderedNumber);
			List<OrderDetails> findByOrderNumber = repository.findByOrder(order);
			BigDecimal total = new BigDecimal(0);
			for (OrderDetails orderDetail : findByOrderNumber) {
				BigDecimal result = (orderDetail.getPriceEach()
						.multiply(BigDecimal.valueOf(orderDetail.getQuantityOrdered())));
				total = total.add(result);
			}

			responseDto.setMessage(total.toString());
			return responseDto;
		} catch (Exception e) {
			throw new OrderDetailsNotFoundException("Order details not found");
		}
	}

	@Override
	public ResponseDto getAllTotal() {
		List<OrderDetails> findAll = repository.findAll();
		BigDecimal total = new BigDecimal(0);
		Integer soldQuantity = 0;
		for (OrderDetails orderDetail : findAll) {
			BigDecimal result = (orderDetail.getPriceEach()
					.multiply(BigDecimal.valueOf(orderDetail.getQuantityOrdered())));
			total = total.add(result);
			soldQuantity += orderDetail.getQuantityOrdered();
		}
		String totalSale = total.toString();
		String quantityTotal = soldQuantity.toString();
		responseDto.setMessage("Total sale amount= " + totalSale + " total quantities sold: " + quantityTotal);
		return responseDto;
	}

	public Products getProductbyProductCode(String productCode) {
		List<OrderDetails> findAll = repository.findAll();

		for (OrderDetails orderDetail : findAll) {
			if (orderDetail.getProduct().getProductCode().equals(productCode)) {
				return orderDetail.getProduct();
			}
		}
		return null;
	}

	public Orders getOrderbyOrderNumber(Integer orderNumber) throws OrderDetailsNotFoundException {
		List<OrderDetails> findAll = repository.findAll();

		for (OrderDetails orderDetail : findAll) {
			if (orderDetail.getOrder().getOrderNumber().equals(orderNumber)) {
				return orderDetail.getOrder();
			}
		}
		throw new OrderDetailsNotFoundException();
	}

	@Override
	public List<OrderDetailsDto> getOrderDetailsByOrderNumber(Integer orderNumber)
			throws OrderDetailsNotFoundException {

		List<OrderDetails> findAll = repository.findAll();
		List<OrderDetailsDto> dtos = new ArrayList<>();
		for (OrderDetails orderDetail : findAll) {
			if (orderDetail.getOrder().getOrderNumber().equals(orderNumber)) {
				OrderDetailsDto dto = new OrderDetailsDto();
				dto.setOrderNumber(orderDetail.getOrder().getOrderNumber());
				dto.setProductCode(orderDetail.getProduct().getProductCode());
				BeanUtils.copyProperties(orderDetail, dto);
				dtos.add(dto);

			}
		}
		if (dtos.isEmpty()) {
			throw new OrderDetailsNotFoundException("Order details not found");
		}
		return dtos;
	}

	@Override
	public List<OrderDetailsDto> getOrderDetailsByProductCode(String productCode) throws OrderDetailsNotFoundException {

		List<OrderDetails> findAll = repository.findAll();
		List<OrderDetailsDto> dtos = new ArrayList<>();
		for (OrderDetails orderDetail : findAll) {
			if (orderDetail.getProduct().getProductCode().equals(productCode)) {
				OrderDetailsDto dto = new OrderDetailsDto();
				dto.setOrderNumber(orderDetail.getOrder().getOrderNumber());
				dto.setProductCode(orderDetail.getProduct().getProductCode());
				BeanUtils.copyProperties(orderDetail, dto);
				dtos.add(dto);

			}
		}
		if (dtos.isEmpty()) {
			throw new OrderDetailsNotFoundException("Order details not found");
		}
		return dtos;
	}

	@Override
	public ResponseDto getCountByProductCode(String productCode) {
		Integer count = getOrderDetailsByProductCode(productCode).size();
		responseDto.setMessage("Total count is " + count);
		return responseDto;
	}

//	@Override
	public Integer getCountByOrderNumber(Integer orderNumber) {
		Integer count = getOrderDetailsByOrderNumber(orderNumber).size();
		return count;
	}

	@Override
	public List<OrderDetailsDto> getMaxOrderDetails() {
		List<OrderDetails> detailsDto = repository.getMaxOrderDetails();
		List<OrderDetailsDto> dtos = new ArrayList<>();
		for (OrderDetails orderDetail : detailsDto) {
			OrderDetailsDto dto = new OrderDetailsDto();
			dto.setOrderNumber(orderDetail.getOrder().getOrderNumber());
			dto.setProductCode(orderDetail.getProduct().getProductCode());
			BeanUtils.copyProperties(orderDetail, dto);
			dtos.add(dto);

		}
		return dtos;
	}

	@Override
	public Boolean existsByProductAndCustomerId(String productCode, Integer orderNumber) {
		return (repository.existsById(new OrderDetailsId(orderNumber, productCode)));
	}

	@Override
	public ResponseDto updateOrderDetailsById(String productCode, Integer orderNumber, OrderDetailsDto dto) {
		Optional<OrderDetails> orderDetailsOpt = repository.findById(new OrderDetailsId(orderNumber, productCode));

		Optional<Products> productOpt = productRepository.findById(productCode);
		Optional<Orders> orderOpt = orderRepository.findById(orderNumber);

		if (!orderDetailsOpt.isPresent() || productOpt.isEmpty() || orderOpt.isEmpty()) {
			throw new OrderDetailsNotFoundException("OrderDetails or Product or Order not found.");
		}
		OrderDetails orderDetail = orderDetailsOpt.get();
		BeanUtils.copyProperties(dto, orderDetail);
		orderDetail.setProduct(productOpt.get());
		orderDetail.setOrder(orderOpt.get());
		repository.save(orderDetail);

		responseDto.setMessage("OrderDetails details updated successfully");
		return responseDto;
	}

}
