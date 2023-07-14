
package com.sprint.classicmodelsbussiness.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sprint.classicmodelsbussiness.dto.CustomersDto;
import com.sprint.classicmodelsbussiness.dto.EmployeeDto;
import com.sprint.classicmodelsbussiness.dto.OrderDetailsDto;
import com.sprint.classicmodelsbussiness.dto.OrderDto;
import com.sprint.classicmodelsbussiness.dto.OrderProductsStaffDto;
import com.sprint.classicmodelsbussiness.dto.OrdersPaymentsDto;
import com.sprint.classicmodelsbussiness.dto.PaymentsDto;
import com.sprint.classicmodelsbussiness.dto.ProductDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;
import com.sprint.classicmodelsbussiness.entity.Customers;
import com.sprint.classicmodelsbussiness.entity.Employees;
import com.sprint.classicmodelsbussiness.entity.Payments;
import com.sprint.classicmodelsbussiness.exception.CustomersNotFoundException;
import com.sprint.classicmodelsbussiness.exception.EmployeeNotFoundException;
import com.sprint.classicmodelsbussiness.exception.OrderNotFoundException;
import com.sprint.classicmodelsbussiness.repository.CustomersRepository;
import com.sprint.classicmodelsbussiness.repository.EmployeeRepository;
import com.sprint.classicmodelsbussiness.repository.PaymentsRepository;
import com.sprint.classicmodelsbussiness.service.CustomersService;

@Service

public class CustomersServiceImpl implements CustomersService {

	@Autowired

	public CustomersRepository repository;

	@Autowired

	public PaymentsRepository paymentsRepository;

	@Autowired

	public EmployeeRepository empRepository;

	@Autowired

	public OrderServiceImpl orderImpl;

	@Autowired

	public OrderDetailsServiceImpl orderDetailsImpl;

	@Autowired

	public ProductServiceImpl prodImpl;

	@Autowired

	public EmployeeServiceImpl empImpl;

	@Autowired

	public PaymentsServiceImpl paymentImpl;

	ResponseDto responseDto = new ResponseDto();

	public CustomersServiceImpl(CustomersRepository cusRepository) {
		this.repository = cusRepository;
	}

	@Override

	public ResponseDto saveCustomer(CustomersDto customersDto) {

		Customers customers = new Customers();

		customers.setContactFirstName(customersDto.getContactFirstName());
		customers.setContactLastName(customersDto.getContactLastName());
		customers.setCity(customersDto.getCity());
		customers.setCountry(customersDto.getCountry());
		customers.setCustomerName(customersDto.getCustomerName());
		customers.setAddressLine1(customersDto.getAddressLine1());
		customers.setAddressLine2(customersDto.getAddressLine2());
		customers.setPhone(customersDto.getPhone());
		customers.setCreditLimit(customersDto.getCreditLimit());
		customers.setState(customersDto.getState());
		customers.setPostalCode(customersDto.getPostalCode());
		customers.setSalesRepEmployeeNumber(empRepository.findById(customersDto.getSalesRepEmployeeNumber()).get());

		// BeanUtils.copyProperties(customersDto, customers);

		repository.save(customers);

		responseDto.setMessage("Record Created Successfully");

		return responseDto;

	}

	@Override

	public List<CustomersDto> getCustomerByCustomerName(String customerName) {

		try {

			List<Customers> byName = repository.findByCustomerName(customerName);

			List<CustomersDto> dtos = new ArrayList<>();

			if (byName.isEmpty()) {

				throw new Exception();

			}

			for (Customers cum : byName) {

				CustomersDto cusDto = new CustomersDto();

				BeanUtils.copyProperties(cum, cusDto);

				dtos.add(cusDto);

			}

			return dtos;

		} catch (Exception e) {

			throw new CustomersNotFoundException("Customer Details not found " + customerName);

		}

	}

	@Override

	public List<CustomersDto> getAllCustomers() {

		List<Customers> findAll = repository.findAll();

		List<CustomersDto> dtos = new ArrayList<>();

		for (Customers ofc : findAll) {

			CustomersDto dto = new CustomersDto();

			BeanUtils.copyProperties(ofc, dto);

			if (ofc.getSalesRepEmployeeNumber() != null) {

				dto.setSalesRepEmployeeNumber(ofc.getSalesRepEmployeeNumber().getEmployeeNumber());

			}

			dtos.add(dto);

		}

		return dtos;

	}

	@Override

	public CustomersDto getCustomerById(Integer customerNumber) {

		Optional<Customers> findById = repository.findById(customerNumber);

		if (findById.isPresent()) {

			CustomersDto dto = new CustomersDto();

			BeanUtils.copyProperties(findById.get(), dto);

			return dto;

		}

		throw new CustomersNotFoundException("Customer Details not found " + customerNumber);

	}

	@Override

	public List<CustomersDto> getCustomersByCity(String city) {

		try {

			List<Customers> byName = repository.findByCity(city);

			List<CustomersDto> dtos = new ArrayList<>();

			if (byName.isEmpty()) {

				throw new Exception();

			}

			for (Customers cum : byName) {

				CustomersDto cusDto = new CustomersDto();

				BeanUtils.copyProperties(cum, cusDto);

				dtos.add(cusDto);

			}

			return dtos;

		} catch (Exception e) {

			throw new CustomersNotFoundException("No Customer Found " + city);

		}

	}

	@Override

	public List<CustomersDto> getCustomerByPhone(String phone) {

		List<Customers> byPhone = repository.findByPhone(phone);

		List<CustomersDto> dtos = new ArrayList<>();

		for (Customers cus : byPhone) {

			CustomersDto cusDto = new CustomersDto();

			BeanUtils.copyProperties(cus, cusDto);

			dtos.add(cusDto);

		}

		return dtos;

	}

	@Override

	public List<CustomersDto> getCustomersByCountry(String country) {

		List<Customers> byCountry = repository.findByCountry(country);

		List<CustomersDto> dtos = new ArrayList<>();

		for (Customers cus : byCountry) {

			CustomersDto dto = new CustomersDto();

			BeanUtils.copyProperties(cus, dto);

			dtos.add(dto);

		}

		return dtos;

	}

	@Override

	public List<CustomersDto> getCustomerBycontactFirstName(String contactFirstName) {

		List<Customers> bycontactFirstName = repository.findByContactFirstName(contactFirstName);

		List<CustomersDto> dtos = new ArrayList<>();

		for (Customers cus : bycontactFirstName) {

			CustomersDto dto = new CustomersDto();

			BeanUtils.copyProperties(cus, dto);

			dtos.add(dto);

		}

		return dtos;

	}

	@Override

	public List<CustomersDto> getCustomerBycontactLastName(String contactLastName) {

		List<Customers> bycontactLastName = repository.findByContactLastName(contactLastName);

		List<CustomersDto> dtos = new ArrayList<>();

		for (Customers cus : bycontactLastName) {

			CustomersDto dto = new CustomersDto();

			BeanUtils.copyProperties(cus, dto);

			dtos.add(dto);

		}

		return dtos;

	}

	@Override

	public List<CustomersDto> getCustomersByCreditLimit(BigDecimal creditLimit) {

		List<Customers> byCreditLimit = repository.findByCreditLimit(creditLimit);

		List<CustomersDto> dtos = new ArrayList<>();

		for (Customers cus : byCreditLimit) {

			CustomersDto dto = new CustomersDto();

			BeanUtils.copyProperties(cus, dto);

			dtos.add(dto);

		}

		return dtos;

	}

	@Override

	public List<CustomersDto> getCustomersBySalesRepEmployeeNumber(Integer salesRepEmployeeNumber) {

		try {

			List<Customers> bySalesRepEmployeeNumber = repository

					.findBySalesRepEmployeeNumberEmployeeNumber(salesRepEmployeeNumber);

			List<CustomersDto> dtos = new ArrayList<>();

			if (bySalesRepEmployeeNumber.isEmpty()) {

				throw new Exception();

			}

			for (Customers cus : bySalesRepEmployeeNumber) {

				CustomersDto dto = new CustomersDto();

				BeanUtils.copyProperties(cus, dto);

				if (cus.getSalesRepEmployeeNumber() != null) {

					dto.setSalesRepEmployeeNumber(cus.getSalesRepEmployeeNumber().getEmployeeNumber());

				}

				dtos.add(dto);

			}

			return dtos;

		} catch (Exception e) {

			throw new CustomersNotFoundException("No Customer Found " + salesRepEmployeeNumber);

		}

	}

	@Override

	public List<CustomersDto> getCustomersByPostalCode(String postalCode) {

		List<Customers> byPostalCode = repository.findByPostalCode(postalCode);

		List<CustomersDto> dtos = new ArrayList<>();

		for (Customers cus : byPostalCode) {

			CustomersDto dto = new CustomersDto();

			BeanUtils.copyProperties(cus, dto);

			dtos.add(dto);

		}

		return dtos;

	}

	@Override

	public CustomersDto updateNameOfCustomerByCustomerNumber(Integer customerNumber, String customerName) {

		try {

			CustomersDto customersDto = getCustomerById(customerNumber);

			customersDto.setCustomerName(customerName);

			Customers customers = new Customers();

			BeanUtils.copyProperties(customersDto, customers);

			repository.save(customers);

			return customersDto;

		} catch (Exception e) {

			throw new CustomersNotFoundException("No Customer Found " + customerNumber);

		}

	}

	@Override

	public CustomersDto updateContactLastNameByCustomerNumber(Integer customerNumber, String contactLastName) {

		try {

			CustomersDto customersDto = getCustomerById(customerNumber);

			customersDto.setContactLastName(contactLastName);

			Customers customers = new Customers();

			BeanUtils.copyProperties(customersDto, customers);

			repository.save(customers);

			return customersDto;

		} catch (Exception e) {

			throw new CustomersNotFoundException("No Customer Found " + customerNumber);

		}

	}

	@Override

	public CustomersDto updateContactFirstNameByCustomerNumber(Integer customerNumber, String contactFirstName) {

		try {

			CustomersDto customersDto = getCustomerById(customerNumber);

			customersDto.setContactFirstName(contactFirstName);

			Customers customers = new Customers();

			BeanUtils.copyProperties(customersDto, customers);

			repository.save(customers);

			return customersDto;

		} catch (Exception e) {

			throw new CustomersNotFoundException("No Customer Found " + customerNumber);

		}

	}

	@Override

	public ResponseDto updateAddressByCustomerNumber(Integer customerNumber, CustomersDto customersDto) {

		try {

			Optional<Customers> customers = repository.findById(customerNumber);

			customers.get().setAddressLine1(customersDto.getAddressLine1());

			customers.get().setAddressLine2(customersDto.getAddressLine2());

			CustomersDto customersDto2 = new CustomersDto();

			BeanUtils.copyProperties(customers.get(), customersDto2);

			repository.save(customers.get());

			responseDto.setMessage("customer address updated successfully");

			return responseDto;

		} catch (Exception e) {

			throw new CustomersNotFoundException("No Customer Found " + customerNumber);

		}

	}

	@Override

	public List<CustomersDto> getCustomersBetweenGivenRangeOfCredit(BigDecimal start, BigDecimal end) {

		try {

			List<Customers> customersList = repository.findByCreditLimitBetween(start, end);

			List<CustomersDto> dtos = new ArrayList<>();

			if (customersList.isEmpty()) {

				throw new Exception();

			}

			for (Customers customers : customersList) {

				CustomersDto dto = new CustomersDto();

				BeanUtils.copyProperties(customers, dto);

				dtos.add(dto);

			}

			return dtos;

		} catch (Exception e) {

			throw new CustomersNotFoundException("No Customers Found within the specified credit range");

		}

	}

	@Override

	public List<CustomersDto> getCustomersWhereCreditlimitIsLowerThan(BigDecimal creditlimit) {

		try {

			List<Customers> customersList = repository.findByCreditLimitLessThan(creditlimit);

			List<CustomersDto> dtos = new ArrayList<>();

			if (customersList.isEmpty()) {

				throw new Exception();

			}

			for (Customers customers : customersList) {

				CustomersDto dto = new CustomersDto();

				BeanUtils.copyProperties(customers, dto);

				dtos.add(dto);

			}

			return dtos;

		} catch (Exception e) {

			throw new CustomersNotFoundException("No Customers Found with a credit limit lower than " + creditlimit);

		}

	}

	@Override

	public List<CustomersDto> getCustomersWhereCreditLimitIsGreaterThan(BigDecimal creditlimit) {

		try {

			List<Customers> customersList = repository.findByCreditLimitGreaterThan(creditlimit);

			List<CustomersDto> dtos = new ArrayList<>();

			if (customersList.isEmpty()) {

				throw new Exception();

			}

			for (Customers customers : customersList) {

				CustomersDto dto = new CustomersDto();

				BeanUtils.copyProperties(customers, dto);

				dtos.add(dto);

			}

			return dtos;

		} catch (Exception e) {

			throw new CustomersNotFoundException("No Customers Found with a credit limit greater than " + creditlimit);

		}

	}

	@Override

	public List<PaymentsDto> getPaymentDetailsForParticularCustomer(Integer customerNumber) {

		// Optional<Customers> findById = repository.findById(customerNumber);

		List<Payments> byCustomerNumber = paymentsRepository.findByCustomersCustomerNumber(customerNumber);

		List<PaymentsDto> dtos = new ArrayList<PaymentsDto>();

		for (Payments payments : byCustomerNumber) {

			PaymentsDto dto = new PaymentsDto();

			BeanUtils.copyProperties(payments, dto);

			dto.setCustomerNumber(customerNumber);

			dtos.add(dto);

		}

		return dtos;

	}

	@Override

	public List<CustomersDto> getCustomersByFirstnameLike(String fn) {

		List<Customers> customersList = repository.findByContactFirstNameLike(fn);

		List<CustomersDto> dtolist = new ArrayList<>();

		for (Customers customer : customersList) {

			CustomersDto dto = new CustomersDto();

			BeanUtils.copyProperties(customer, dto);

			dtolist.add(dto);

		}

		return dtolist;

	}

	@Override

	public List<CustomersDto> getCustomersOnPage(Integer pageSize, Integer pageNo, String sortBy, String sortDir) {

		Page<Customers> customersPage = repository.findAll(

				PageRequest.of(pageNo, pageSize).withSort(Sort.by(Sort.Direction.fromString(sortDir), sortBy)));

		List<CustomersDto> dtosList = new ArrayList<>();

		for (Customers customers : customersPage) {

			CustomersDto customersDto = new CustomersDto();

			BeanUtils.copyProperties(customers, customersDto);

			dtosList.add(customersDto);

		}

		return dtosList;

	}

	@Override
	public List<OrderProductsStaffDto> getOrderProductStaffByCustomer(Integer customerNumber) {
		List<OrderProductsStaffDto> dtoList = new ArrayList<>();

		List<OrderDto> orderList = orderImpl.getOrdersByCustomerNumber(customerNumber);

		for (OrderDto order : orderList) {

			OrderProductsStaffDto dto = new OrderProductsStaffDto();
			dto.setOrder(order);
			List<OrderDetailsDto> orderDetailsList = orderDetailsImpl
					.getOrderDetailsByOrderNumber(order.getOrderNumber());
			List<ProductDto> productList = new ArrayList<>();
			for (OrderDetailsDto orderDetail : orderDetailsList) {

				ProductDto prod = prodImpl.getProductByCode(orderDetail.getProductCode());
				productList.add(prod);
			}
			dto.setProductList(productList);
			Customers cus = repository.findById(customerNumber).get();
			if (cus.getSalesRepEmployeeNumber() != null) {
				EmployeeDto emp = empImpl.getEmployeeById(cus.getSalesRepEmployeeNumber().getEmployeeNumber());
				dto.setEmployee(emp);
			}

			dtoList.add(dto);

		}

		return dtoList;
	}

	@Override
	public List<OrdersPaymentsDto> getOrderPaymentsByCustomer(String customerName) {
		List<OrdersPaymentsDto> dtoList = new ArrayList<>();
		OrdersPaymentsDto dto = new OrdersPaymentsDto();
		List<Customers> cusList = repository.findByCustomerName(customerName);
		for (Customers cus : cusList) {
			List<OrderDto> orderList = orderImpl.getOrdersByCustomerNumber(cus.getCustomerNumber());
			dto.setOrderList(orderList);
			List<PaymentsDto> paymentList = paymentImpl.getAllPaymentDetailsByCustomerNumber(cus.getCustomerNumber());
			dto.setPaymentList(paymentList);
			dtoList.add(dto);
		}

		return dtoList;
	}

	@Override
	public Boolean existsByCustomerNumber(Integer customerNumber) {
		return repository.existsByCustomerNumber(customerNumber);
	}

	@Override

	public ResponseDto updateCustomerById(Integer customerNumber, CustomersDto dto) {
		Optional<Customers> customerOpt = repository.findById(customerNumber);
		if (!customerOpt.isPresent()) {
			throw new OrderNotFoundException("Customer not found.");
		}
		Customers customer = customerOpt.get();
		BeanUtils.copyProperties(dto, customer);
		customer.setCustomerNumber(customerNumber);
		if (dto.getSalesRepEmployeeNumber() != null) {
			Optional<Employees> empOpt = empRepository.findById(dto.getSalesRepEmployeeNumber());
			if (empOpt.isEmpty()) {

				throw new EmployeeNotFoundException("SalesRepEmployee not found.");
			}
			customer.setSalesRepEmployeeNumber(empOpt.get());
		}
		else {
			customer.setSalesRepEmployeeNumber(null);
		}
		repository.save(customer);
		responseDto.setMessage("Customer details updated successfully");
		return responseDto;

	}

}
