package com.sprint.classicmodelsbussiness.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.classicmodelsbussiness.dto.CustomersDto;
import com.sprint.classicmodelsbussiness.dto.OrderProductsStaffDto;
import com.sprint.classicmodelsbussiness.dto.OrdersPaymentsDto;
import com.sprint.classicmodelsbussiness.dto.PaymentsDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;
import com.sprint.classicmodelsbussiness.service.CustomersService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200/")

public class CustomersController {

	@Autowired
	public CustomersService customersService;

	@PostMapping("/v1/customers")
	public ResponseEntity<ResponseDto> saveCustomers(@Valid @RequestBody CustomersDto dto) {
		ResponseDto responseDto = customersService.saveCustomer(dto);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
	}

	@GetMapping("v1/customers/all_customers")
	public ResponseEntity<List<CustomersDto>> getAllCustomers() {
		List<CustomersDto> cusDto = customersService.getAllCustomers();
		return new ResponseEntity<List<CustomersDto>>(cusDto, HttpStatus.OK);
	}

	@GetMapping("v1/v1/customers/{customerNumber}")
	public ResponseEntity<CustomersDto> getCustomerById(@Validated @PathVariable Integer customerNumber) {
		CustomersDto customersDto = customersService.getCustomerById(customerNumber);
		return new ResponseEntity<CustomersDto>(customersDto, HttpStatus.OK);
	}

	@GetMapping("v1/customers/customerName/{customerName}")
	public ResponseEntity<List<CustomersDto>> getCustomerByCustomerName(@Validated @PathVariable String customerName) {
		List<CustomersDto> customersDto = customersService.getCustomerByCustomerName(customerName);
		return new ResponseEntity<List<CustomersDto>>(customersDto, HttpStatus.FOUND);
	}

	@GetMapping("v1/customers/city/{city}")
	public ResponseEntity<List<CustomersDto>> getCustomerByCity(@Validated @PathVariable String city) {
		List<CustomersDto> customersDtos = customersService.getCustomersByCity(city);
		return new ResponseEntity<List<CustomersDto>>(customersDtos, HttpStatus.FOUND);
	}

	@GetMapping("v1/customers/country/{country}")
	public ResponseEntity<List<CustomersDto>> getCustomerByCountry(@Validated @PathVariable String country) {
		List<CustomersDto> customersDtos = customersService.getCustomersByCountry(country);
		return new ResponseEntity<List<CustomersDto>>(customersDtos, HttpStatus.FOUND);
	}

	@GetMapping("v1/customers/phone/{phone}")
	public ResponseEntity<List<CustomersDto>> getCustomerByPhone(@Validated @PathVariable String phone) {
		List<CustomersDto> customerDtos = customersService.getCustomerByPhone(phone);
		return new ResponseEntity<List<CustomersDto>>(customerDtos, HttpStatus.FOUND);
	}

	@GetMapping("v1/customers/contactFirstName/{contactFirstName}")
	public ResponseEntity<List<CustomersDto>> getCustomerBycontactFirstName(
			@Validated @PathVariable String contactFirstName) {
		List<CustomersDto> customersDtos = customersService.getCustomerBycontactFirstName(contactFirstName);
		return new ResponseEntity<List<CustomersDto>>(customersDtos, HttpStatus.FOUND);
	}

	@GetMapping("v1/customers/contactLastName/{contactLastName}")
	public ResponseEntity<List<CustomersDto>> getCustomerBycontactLastName(
			@Validated @PathVariable String contactLastName) {
		List<CustomersDto> customersDtos = customersService.getCustomerBycontactLastName(contactLastName);
		return new ResponseEntity<List<CustomersDto>>(customersDtos, HttpStatus.FOUND);
	}

	@GetMapping("v1/customers/creditLimit/{creditLimit}")
	public ResponseEntity<List<CustomersDto>> getCustomersByCreditLimit(
			@Validated @PathVariable BigDecimal creditLimit) {
		List<CustomersDto> customersDtos = customersService.getCustomersByCreditLimit(creditLimit);
		return new ResponseEntity<List<CustomersDto>>(customersDtos, HttpStatus.FOUND);
	}

	@GetMapping("v1/customers/postalCode/{postalCode}")
	public ResponseEntity<List<CustomersDto>> getCustomersByPostalCode(@PathVariable String postalCode) {
		List<CustomersDto> customersDtos = customersService.getCustomersByPostalCode(postalCode);
		return new ResponseEntity<List<CustomersDto>>(customersDtos, HttpStatus.FOUND);

	}

	@GetMapping("v1/customers/salesRepEmployeeNumber/{salesRepEmployeeNumber}")
	public ResponseEntity<List<CustomersDto>> getCustomersBySalesRepEmployeeNumber(
			@PathVariable Integer salesRepEmployeeNumber) {
		List<CustomersDto> customersDtos = customersService
				.getCustomersBySalesRepEmployeeNumber(salesRepEmployeeNumber);
		return new ResponseEntity<List<CustomersDto>>(customersDtos, HttpStatus.FOUND);
	}

	@PutMapping("v1/customers/customerName/{customerNumber}/{customerName}")
	public ResponseEntity<CustomersDto> updateNameOfCustomerByCustomerNumber(
			@Validated @PathVariable Integer customerNumber, @PathVariable String customerName) {
		CustomersDto customersDto = customersService.updateNameOfCustomerByCustomerNumber(customerNumber, customerName);
		return new ResponseEntity<CustomersDto>(customersDto, HttpStatus.OK);
	}

	@PutMapping("v1/customers/contactLastName/{customerNumber}/{contactLastName}")
	public ResponseEntity<CustomersDto> updateContactLastNameByCustomerNumber(
			@Validated @PathVariable Integer customerNumber, @PathVariable String contactLastName) {
		CustomersDto customersDto = customersService.updateContactLastNameByCustomerNumber(customerNumber,
				contactLastName);
		return new ResponseEntity<CustomersDto>(customersDto, HttpStatus.OK);
	}

	@PutMapping("v1/customers/contactFirstName/{customerNumber}/{contactFirstName}")
	public ResponseEntity<CustomersDto> updateContactFirstNameByCustomerNumber(
			@Valid @PathVariable Integer customerNumber, @PathVariable String contactFirstName) {
//        if (contactFirstName.length() < 3 || contactFirstName.length() > 50) {
//            throw new CustomersNotFoundException("contactFirstName should be minimum 3 and maximum 50 characters long");
//            
//        }
		CustomersDto customersDto = customersService.updateContactFirstNameByCustomerNumber(customerNumber,
				contactFirstName);
		return new ResponseEntity<CustomersDto>(customersDto, HttpStatus.OK);

	}

	@PutMapping("v1/customers/address/{customerNumber}")
	public ResponseEntity<ResponseDto> updateAddressByCustomerNumber(@PathVariable Integer customerNumber,
			@RequestBody CustomersDto customersDto) {
		ResponseDto responseDto = customersService.updateAddressByCustomerNumber(customerNumber, customersDto);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
	}

	@GetMapping("v1/customers/givenRangeOfCredit/{start}/{end}")
	public ResponseEntity<List<CustomersDto>> getCustomersBetweenGivenRangeOfCredit(@PathVariable BigDecimal start,
			@PathVariable BigDecimal end) {
		List<CustomersDto> customersDtos = customersService.getCustomersBetweenGivenRangeOfCredit(start, end);
		return new ResponseEntity<List<CustomersDto>>(customersDtos, HttpStatus.FOUND);
	}

	@GetMapping("v1/customers/creditlimitIsLowerThan/{creditlimit}")
	public ResponseEntity<List<CustomersDto>> getCustomersWhereCreditlimitIsLowerThan(
			@PathVariable BigDecimal creditlimit) {
		List<CustomersDto> customersDtos = customersService.getCustomersWhereCreditlimitIsLowerThan(creditlimit);
		return new ResponseEntity<List<CustomersDto>>(customersDtos, HttpStatus.FOUND);
	}

	@GetMapping("v1/customers/creditlimitIsGreaterThan/{creditlimit}")
	public ResponseEntity<List<CustomersDto>> getCustomersWhereCreditLimitIsGreaterThan(
			@PathVariable BigDecimal creditlimit) {
		List<CustomersDto> customersDtos = customersService.getCustomersWhereCreditLimitIsGreaterThan(creditlimit);
		return new ResponseEntity<List<CustomersDto>>(customersDtos, HttpStatus.FOUND);

	}

	@GetMapping("v1/customers/paymentDetails/{customerNumber}")
	public ResponseEntity<List<PaymentsDto>> getPaymentDetailsForParticularCustomer(
			@PathVariable Integer customerNumber) {
		List<PaymentsDto> paymentsDtos = customersService.getPaymentDetailsForParticularCustomer(customerNumber);
		return new ResponseEntity<List<PaymentsDto>>(paymentsDtos, HttpStatus.FOUND);
	}

	@GetMapping("v1/customers/getbyfirstnamelike/{fn}")
	public ResponseEntity<List<CustomersDto>> getCustomersByFirstnameLike(@PathVariable String fn) {
		List<CustomersDto> customersDtos = customersService.getCustomersByFirstnameLike(fn);
		return new ResponseEntity<List<CustomersDto>>(customersDtos, HttpStatus.FOUND);
	}

	@GetMapping("v1/customers/{customer_id}/order_product_staffdetails")
	public ResponseEntity<List<OrderProductsStaffDto>> getOrderProductStaffByCustomer(
			@PathVariable Integer customer_id) {
		List<OrderProductsStaffDto> customersDto = customersService.getOrderProductStaffByCustomer(customer_id);
		return new ResponseEntity<List<OrderProductsStaffDto>>(customersDto, HttpStatus.FOUND);
	}

	@GetMapping("v1/customers/{customer_name}/order_payment_details")
	public ResponseEntity<List<OrdersPaymentsDto>> getOrderPaymentsByCustomer(@PathVariable String customer_name) {
		List<OrdersPaymentsDto> customersDto = customersService.getOrderPaymentsByCustomer(customer_name);
		return new ResponseEntity<List<OrdersPaymentsDto>>(customersDto, HttpStatus.FOUND);
	}

	@GetMapping("/{pageSize}/{pageNo}/{sortBy}/{sortDir}")
	public ResponseEntity<List<CustomersDto>> getCustomersOnPage(@PathVariable Integer pageSize,
			@PathVariable Integer pageNo, @PathVariable String sortBy, @PathVariable String sortDir) {
		List<CustomersDto> customersPage = customersService.getCustomersOnPage(pageSize, pageNo, sortBy, sortDir);
		return new ResponseEntity<List<CustomersDto>>(customersPage, HttpStatus.OK);
	}

	@PutMapping("v1/customers/update/{customerNumber}")

	public ResponseEntity<ResponseDto> updateCustomerById(@Valid @RequestBody CustomersDto dto,
			@Valid @PathVariable Integer customerNumber) {

		ResponseDto customersDto = customersService.updateCustomerById(customerNumber, dto);

		return new ResponseEntity<ResponseDto>(customersDto, HttpStatus.OK);

	}

}