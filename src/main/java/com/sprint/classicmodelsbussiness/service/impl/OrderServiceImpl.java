package com.sprint.classicmodelsbussiness.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.classicmodelsbussiness.dto.OrderDetailsDto;
import com.sprint.classicmodelsbussiness.dto.OrderDto;
import com.sprint.classicmodelsbussiness.dto.ProductDto;
import com.sprint.classicmodelsbussiness.dto.ProductProductLinesDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;
import com.sprint.classicmodelsbussiness.entity.Customers;
import com.sprint.classicmodelsbussiness.entity.Orders;
import com.sprint.classicmodelsbussiness.entity.Products;
import com.sprint.classicmodelsbussiness.exception.CustomersNotFoundException;
import com.sprint.classicmodelsbussiness.exception.OrderNotFoundException;
import com.sprint.classicmodelsbussiness.repository.CustomersRepository;
import com.sprint.classicmodelsbussiness.repository.OrderDetailsRepository;
import com.sprint.classicmodelsbussiness.repository.OrderRepository;
import com.sprint.classicmodelsbussiness.repository.ProductLinesRepository;
import com.sprint.classicmodelsbussiness.repository.ProductRepository;
import com.sprint.classicmodelsbussiness.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository repository;

	@Autowired
	private CustomersRepository cusRepository;

	@Autowired
	private OrderDetailsRepository orderDetrepository;

	@Autowired
	private ProductRepository prodrepository;

	@Autowired
	private ProductLinesRepository prodLinesRepository;

	private ResponseDto responseDto = new ResponseDto();

	/**
	 * Saves the details of an order.
	 *
	 * @param orderDto The OrderDto object containing the order details to be saved.
	 * @return A ResponseDto indicating the success of the operation.
	 * @throws OrderNotFoundException     If the order number already exists.
	 * @throws CustomersNotFoundException If the customer details are not found.
	 */
	@Override
	public ResponseDto saveOrder(OrderDto orderDto) throws OrderNotFoundException, CustomersNotFoundException {
		if (existsByOrderNumber(orderDto.getOrderNumber())) {
			throw new OrderNotFoundException("Order number already exists");
		}
		Optional<Customers> cust = cusRepository.findById(orderDto.getCustomerNumber());
		if (cust.isEmpty()) {
			throw new CustomersNotFoundException("Customer details not found");
		}
		try {
			Orders order = new Orders();
			order.setCustomer(cust.get());
			BeanUtils.copyProperties(orderDto, order);
			orderDto.setOrderNumber(order.getOrderNumber());
			repository.save(order);
			responseDto.setMessage("Order created successfully");
			return responseDto;
		} catch (Exception e) {
			throw new OrderNotFoundException("Sorry, cannot add order details");
		}
	}

	/**
	 * Retrieves all orders.
	 *
	 * @return A list of OrderDto objects representing all orders.
	 */
	@Override
	public List<OrderDto> getAllOrders() {
		List<Orders> findAll = repository.findAll();
		List<OrderDto> dtos = new ArrayList<>();
		for (Orders prod : findAll) {
			OrderDto dto = new OrderDto();
			dto.setCustomerNumber(prod.getCustomer().getCustomerNumber());
			BeanUtils.copyProperties(prod, dto);
			dtos.add(dto);
		}

		return dtos;
	}

	/**
	 * Updates the shipped date of an order.
	 *
	 * @param orderNumber The order number to update.
	 * @param shippedDate The new shipped date for the order.
	 * @return A ResponseDto indicating the success of the operation.
	 * @throws OrderNotFoundException If the order is not found.
	 */
	@Override
	public ResponseDto updateShippedDate(Integer orderNumber, String shippedDate) throws OrderNotFoundException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(shippedDate, formatter);
		Optional<Orders> orderDto = repository.findById(orderNumber);
		if (orderDto.isPresent()) {
			Orders prod = new Orders();
			prod.setCustomer(orderDto.get().getCustomer());
			orderDto.get().setShippedDate(date);
			BeanUtils.copyProperties(orderDto.get(), prod);
			repository.save(prod);

			responseDto.setMessage("Order's shipped date updated successfully");
			return responseDto;
		}

		throw new OrderNotFoundException("Order details not found");
	}

	/**
	 * Updates the status of an order.
	 *
	 * @param orderNumber The order number to update.
	 * @param status      The new status for the order.
	 * @return A ResponseDto indicating the success of the operation.
	 * @throws OrderNotFoundException If the order is not found.
	 */
	@Override
	public ResponseDto updateOrderStatus(Integer orderNumber, String status) throws OrderNotFoundException {
		Optional<Orders> orderDto = repository.findById(orderNumber);
		if (orderDto.isPresent()) {
			Orders prod = new Orders();
			prod.setCustomer(orderDto.get().getCustomer());
			orderDto.get().setStatus(status);
			BeanUtils.copyProperties(orderDto.get(), prod);
			repository.save(prod);

			responseDto.setMessage("Order status updated successfully");
			return responseDto;
		}

		throw new OrderNotFoundException("Order details not found");
	}

	/**
	 * Retrieves orders by customer number.
	 *
	 * @param customerNumber The customer number to retrieve orders for.
	 * @return A list of OrderDto objects representing the orders.
	 * @throws OrderNotFoundException If no orders are found for the given customer
	 *                                number.
	 */
	@Override
	public List<OrderDto> getOrdersByCustomerNumber(Integer customerNumber) throws OrderNotFoundException {
		Optional<Customers> cust = cusRepository.findById(customerNumber);
		List<Orders> findByCustomerNumber = repository.findByCustomer(cust);
		List<OrderDto> dtos = new ArrayList<>();

		for (Orders prod : findByCustomerNumber) {
			OrderDto dto = new OrderDto();
			dto.setCustomerNumber(prod.getCustomer().getCustomerNumber());
			BeanUtils.copyProperties(prod, dto);
			dtos.add(dto);
		}
		if (dtos.isEmpty()) {
			throw new OrderNotFoundException("Customer details not found");
		}
		return dtos;
	}

	/**
	 * Retrieves an order by order number.
	 *
	 * @param orderNumber The order number to retrieve.
	 * @return The OrderDto object representing the order.
	 * @throws OrderNotFoundException If the order with the given order number is
	 *                                not found.
	 */
	@Override
	public OrderDto getOrdersByOrderNumber(Integer orderNumber) throws OrderNotFoundException {
		Optional<Orders> findByOrderNumber = repository.findByOrderNumber(orderNumber);

		if (findByOrderNumber.isPresent()) {
			OrderDto dto = new OrderDto();
			dto.setCustomerNumber(findByOrderNumber.get().getCustomer().getCustomerNumber());
			BeanUtils.copyProperties(findByOrderNumber.get(), dto);
			return dto;
		}
		throw new OrderNotFoundException("Order details not found");
	}

	/**
	 * Retrieves orders by order date.
	 *
	 * @param orderDate The order date to retrieve orders for.
	 * @return A list of OrderDto objects representing the orders.
	 * @throws OrderNotFoundException If no orders are found for the given order
	 *                                date.
	 */
	@Override
	public List<OrderDto> getOrdersByOrderDate(String orderDate) throws OrderNotFoundException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(orderDate, formatter);
		List<Orders> findByOrderDate = repository.findByOrderDate(date);
		List<OrderDto> dtos = new ArrayList<>();

		for (Orders prod : findByOrderDate) {
			OrderDto dto = new OrderDto();
			dto.setCustomerNumber(prod.getCustomer().getCustomerNumber());
			BeanUtils.copyProperties(prod, dto);
			dtos.add(dto);
		}
		if (dtos.isEmpty()) {
			throw new OrderNotFoundException("Order details not found");
		}
		return dtos;
	}

	/**
	 * Retrieves orders by required date.
	 *
	 * @param requiredDate The required date to retrieve orders for.
	 * @return A list of OrderDto objects representing the orders.
	 * @throws OrderNotFoundException If no orders are found for the given required
	 *                                date.
	 */
	@Override
	public List<OrderDto> getOrdersByRequiredDate(String requiredDate) throws OrderNotFoundException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(requiredDate, formatter);
		List<Orders> findByRequiredDate = repository.findByRequiredDate(date);
		List<OrderDto> dtos = new ArrayList<>();

		for (Orders prod : findByRequiredDate) {
			OrderDto dto = new OrderDto();
			dto.setCustomerNumber(prod.getCustomer().getCustomerNumber());
			BeanUtils.copyProperties(prod, dto);
			dtos.add(dto);
		}
		if (dtos.isEmpty()) {
			throw new OrderNotFoundException("Order details not found");
		}
		return dtos;
	}

	/**
	 * Retrieves orders by shipped date.
	 *
	 * @param shippedDate The shipped date to retrieve orders for.
	 * @return A list of OrderDto objects representing the orders.
	 * @throws OrderNotFoundException If no orders are found for the given shipped
	 *                                date.
	 */
	@Override
	public List<OrderDto> getOrdersByShippedDate(String shippedDate) throws OrderNotFoundException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(shippedDate, formatter);
		List<Orders> findByShippedDate = repository.findByShippedDate(date);
		List<OrderDto> dtos = new ArrayList<>();

		for (Orders prod : findByShippedDate) {
			OrderDto dto = new OrderDto();
			dto.setCustomerNumber(prod.getCustomer().getCustomerNumber());
			BeanUtils.copyProperties(prod, dto);
			dtos.add(dto);
		}
		if (dtos.isEmpty()) {
			throw new OrderNotFoundException("Product shipped details not found");
		}
		return dtos;
	}

	/**
	 * Retrieves orders by order status.
	 *
	 * @param status The order status to retrieve orders for.
	 * @return A list of OrderDto objects representing the orders.
	 * @throws OrderNotFoundException If no orders are found for the given order
	 *                                status.
	 */
	@Override
	public List<OrderDto> getOrdersByOrderStatus(String status) throws OrderNotFoundException {
		List<Orders> findByStatus = repository.findByStatus(status);
		List<OrderDto> dtos = new ArrayList<>();

		for (Orders prod : findByStatus) {
			OrderDto dto = new OrderDto();
			dto.setCustomerNumber(prod.getCustomer().getCustomerNumber());
			BeanUtils.copyProperties(prod, dto);
			dtos.add(dto);
		}
		if (dtos.isEmpty()) {
			throw new OrderNotFoundException("Product details not found");
		}
		return dtos;
	}

	/**
	 * Retrieves orders by order status for a specific customer.
	 *
	 * @param customerNumber The customer number to retrieve orders for.
	 * @param status         The order status to retrieve orders for.
	 * @return A list of OrderDto objects representing the orders.
	 * @throws OrderNotFoundException If no orders are found for the given customer
	 *                                number and order status.
	 */
	@Override
	public List<OrderDto> getOrdersByOrderStatusForCustomer(Integer customerNumber, String status)
			throws OrderNotFoundException {
		List<OrderDto> getOrdersByCustomerNumber = getOrdersByCustomerNumber(customerNumber);
		List<OrderDto> dtos = new ArrayList<>();

		for (OrderDto prod : getOrdersByCustomerNumber) {
			if (prod.getStatus().equals(status)) {
				OrderDto dto = new OrderDto();
				dto.setCustomerNumber(prod.getCustomerNumber());
				BeanUtils.copyProperties(prod, dto);
				dtos.add(dto);
			}
		}
		if (dtos.isEmpty()) {
			throw new OrderNotFoundException("Customer details not found");
		}
		return dtos;
	}

	/**
	 * Retrieves products for a specific order number.
	 *
	 * @param orderNumber The order number to retrieve products for.
	 * @return A list of ProductDto objects representing the products.
	 * @throws OrderNotFoundException If the order is not found.
	 */
	@Override
	public List<ProductDto> getProductsByOrderNumber(Integer orderNumber) throws OrderNotFoundException {
		OrderDetailsServiceImpl orderDetailsImpl = new OrderDetailsServiceImpl(orderDetrepository);
		List<OrderDetailsDto> getOrderDetailsByOrderNumber = orderDetailsImpl.getOrderDetailsByOrderNumber(orderNumber);
		List<ProductDto> dtos = new ArrayList<>();
		for (OrderDetailsDto orderDet : getOrderDetailsByOrderNumber) {
			Products prod = orderDetailsImpl.getProductbyProductCode(orderDet.getProductCode());
			ProductDto dto = new ProductDto();
			dto.setProductLine(prod.getProductLine().getProductLine());
			BeanUtils.copyProperties(prod, dto);
			dtos.add(dto);
		}
		if (dtos.isEmpty()) {
			throw new OrderNotFoundException("Order details not found");
		}
		return dtos;
	}

	/**
	 * Retrieves product names for a specific order number.
	 *
	 * @param orderNumber The order number to retrieve product names for.
	 * @return A list of product names.
	 * @throws OrderNotFoundException If the order is not found.
	 */
	@Override
	public List<String> getProductsNameByOrderNumber(Integer orderNumber) throws OrderNotFoundException {
		OrderDetailsServiceImpl orderDetailsImpl = new OrderDetailsServiceImpl(orderDetrepository);
		List<OrderDetailsDto> getOrderDetailsByOrderNumber = orderDetailsImpl.getOrderDetailsByOrderNumber(orderNumber);
		List<String> dtos = new ArrayList<>();
		for (OrderDetailsDto orderDet : getOrderDetailsByOrderNumber) {
			Products prod = orderDetailsImpl.getProductbyProductCode(orderDet.getProductCode());

			dtos.add(prod.getProductName());
		}
		if (dtos.isEmpty()) {
			throw new OrderNotFoundException("Order details not found");
		}
		return dtos;
	}

	/**
	 * Retrieves all products.
	 *
	 * @return A list of ProductDto objects representing all products.
	 */
	@Override
	public List<ProductDto> getAllProducts() {
		ProductServiceImpl prodImpl = new ProductServiceImpl(prodrepository);
		return prodImpl.getAllProducts();
	}

	/**
	 * Retrieves orders with a delivered status.
	 *
	 * @param status The order status to retrieve orders for.
	 * @return A list of OrderDto objects representing the orders.
	 * @throws OrderNotFoundException If no orders are found with the given status.
	 */
	@Override
	public List<OrderDto> getOrdersByDelivered(String status) throws OrderNotFoundException {
		List<Orders> findByStatus = repository.findByStatus(status);
		List<OrderDto> dtos = new ArrayList<>();

		for (Orders prod : findByStatus) {
			if (prod.getShippedDate().equals(prod.getOrderDate())) {
				OrderDto dto = new OrderDto();
				dto.setCustomerNumber(prod.getCustomer().getCustomerNumber());
				BeanUtils.copyProperties(prod, dto);
				dtos.add(dto);
			}
		}
		if (dtos.isEmpty()) {
			throw new OrderNotFoundException("Order details not found");
		}
		return dtos;
	}

	/**
	 * Retrieves products with their product lines for orders shipped on a specific
	 * date.
	 *
	 * @return A list of lists, where each inner list contains
	 *         ProductProductLinesDto objects representing the products with their
	 *         product lines.
	 */
	@Override
	public List<List<ProductProductLinesDto>> getProductsForShippedDate() {
		List<OrderDto> dtos = getOrdersByShippedDate("2023-01-01");
		List<List<ProductProductLinesDto>> prodList = new ArrayList<>();

		for (OrderDto dto : dtos) {
			List<ProductProductLinesDto> plist = new ArrayList<>();
			List<ProductDto> prodDtos = getProductsByOrderNumber(dto.getOrderNumber());

			for (ProductDto prodDto : prodDtos) {
				ProductProductLinesDto productProductLinesDto = new ProductProductLinesDto(prodDto,
						prodLinesRepository.findById(prodDto.getProductLine()).get());
				plist.add(productProductLinesDto);
			}
			prodList.add(plist);
		}
		return prodList;
	}

	/**
	 * Checks if an order number already exists.
	 *
	 * @param orderNumber The order number to check.
	 * @return true if the order number exists, false otherwise.
	 */
	@Override
	public Boolean existsByOrderNumber(Integer orderNumber) {
		return repository.existsByOrderNumber(orderNumber);
	}

	/**
	 * Updates an order by its order number.
	 *
	 * @param orderNumber The order number to update.
	 * @param dto         The OrderDto object containing the updated order details.
	 * @return A ResponseDto indicating the success of the operation.
	 */
	@Override
	public ResponseDto updateOrderById(Integer orderNumber, OrderDto dto) {
		Optional<Orders> orderOpt = repository.findById(orderNumber);

		Optional<Customers> findByIdCus = cusRepository.findById(dto.getCustomerNumber());

		if (!orderOpt.isPresent() || !findByIdCus.isPresent()) {
			throw new OrderNotFoundException("Order or Customer not found.");
		}
		Orders order = orderOpt.get();
		
		BeanUtils.copyProperties(dto, order);
		order.setOrderNumber(orderNumber);
		order.setCustomer(findByIdCus.get());

		repository.save(order);

		responseDto.setMessage("Order details updated successfully");
		return responseDto;
	}

}
