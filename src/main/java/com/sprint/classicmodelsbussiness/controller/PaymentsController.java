package com.sprint.classicmodelsbussiness.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.classicmodelsbussiness.dto.CustomersDto;
import com.sprint.classicmodelsbussiness.dto.PaymentsDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;
import com.sprint.classicmodelsbussiness.service.PaymentsService;

@RestController
@RequestMapping("api")
public class PaymentsController {

	@Autowired
	public PaymentsService paymentsService;

	@PostMapping("v1/payments/save")
	public ResponseEntity<ResponseDto> savePayments(@Valid @RequestBody PaymentsDto payment) {
		ResponseDto dto = paymentsService.savePayments(payment);
		return new ResponseEntity<ResponseDto>(dto, HttpStatus.OK);
	}

	@GetMapping("v1/payments/getAll")
	public ResponseEntity<List<PaymentsDto>> getAllPaymentsDetails() {
		List<PaymentsDto> paymentsDtos = paymentsService.getAllPaymentsDetails();
		return new ResponseEntity<List<PaymentsDto>>(paymentsDtos, HttpStatus.OK);
	}

	@GetMapping("v1/payments/paymentDate/{paymentDate}")
	public ResponseEntity<List<PaymentsDto>> getAllPaymentDetailsByPaymentDate(@PathVariable String paymentDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(paymentDate, formatter);
		List<PaymentsDto> paymentsDto = paymentsService.getAllPaymentDetailsByPaymentDate(date);
		return new ResponseEntity<List<PaymentsDto>>(paymentsDto, HttpStatus.OK);
	}

	@GetMapping("v1/payments/allCustomers/{paymentDate}")
	public ResponseEntity<List<CustomersDto>> getAllCustomersWhoPaidOnGivenDate(@PathVariable String paymentDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse(paymentDate, formatter);
		List<CustomersDto> customersDtos = paymentsService.getAllCustomersWhoPaidOnGivenDate(date);
		return new ResponseEntity<List<CustomersDto>>(customersDtos, HttpStatus.OK);
	}

	@GetMapping("v1/payments/GivenDateRange/{startPayDate}/{endPayDate}")
	public ResponseEntity<List<CustomersDto>> getCustomersDonePaymentInGivenDateRange(@PathVariable String startPayDate,
			@PathVariable String endPayDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date1 = LocalDate.parse(startPayDate, formatter);
		LocalDate date2 = LocalDate.parse(endPayDate, formatter);
		List<CustomersDto> customersDtos = paymentsService.getCustomersDonePaymentInGivenDateRange(date1, date2);
		return new ResponseEntity<List<CustomersDto>>(customersDtos, HttpStatus.OK);
	}

	@GetMapping("v1/payments/customerNumber/{customerNumber}")
	public ResponseEntity<List<PaymentsDto>> getAllPaymentDetailsForSpecificCustomer(
			@PathVariable Integer customerNumber) {
		List<PaymentsDto> paymentsDtos = paymentsService.getAllPaymentDetailsByCustomerNumber(customerNumber);
		return new ResponseEntity<List<PaymentsDto>>(paymentsDtos, HttpStatus.OK);
	}

	@GetMapping("v1/payments/checkNumber/{checkNumber}")
	public ResponseEntity<PaymentsDto> getAllPaymentDetailsByCheckNumber(@PathVariable String checkNumber) {
		PaymentsDto paymentsDtos = paymentsService.getAllPaymentDetailsByCheckNumber(checkNumber);
		return new ResponseEntity<PaymentsDto>(paymentsDtos, HttpStatus.OK);

	}

	@PutMapping("v1/payments/amount/{customerNumber}/{checkNumber}/{newAmount}")
	public ResponseEntity<ResponseDto> updateAmountOfSpecificCheckOfCustomer(@PathVariable Integer customerNumber,
			@PathVariable String checkNumber, @PathVariable BigDecimal newAmount) {
		ResponseDto responseDto = paymentsService.updateAmountOfSpecificCheckOfCustomer(customerNumber, checkNumber,
				newAmount);
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.FOUND);

	}

	@GetMapping("v1/payments/toatlAmount/{customerNumber}")
	public ResponseEntity<BigDecimal> getTotalAmountOfPaymentMadeBySpecificCustomer(
			@PathVariable Integer customerNumber) {
		BigDecimal amt = paymentsService.getTotalAmountOfPaymentMadeBySpecificCustomer(customerNumber);
		return new ResponseEntity<BigDecimal>(amt, HttpStatus.FOUND);
	}

	@GetMapping("v1/payments/CustomersByCheckNumber/{checkNumber}")
	public ResponseEntity<CustomersDto> getCustomersByCheckNumber(@PathVariable String checkNumber) {
		CustomersDto custDto = paymentsService.getCustomersByCheckNumber(checkNumber);
		return new ResponseEntity<CustomersDto>(custDto, HttpStatus.FOUND);
	}

	@PutMapping("v1/payments/{checkNumber}")
	public ResponseEntity<ResponseDto> updatePaymentById(@Valid @PathVariable String checkNumber,
			@Valid @RequestBody PaymentsDto paymentsDto) {
		ResponseDto string = paymentsService.updatePaymentById(checkNumber, paymentsDto);
		return new ResponseEntity<ResponseDto>(string, HttpStatus.OK);
		// return new ResponseEntity<String>
		// (paymentsService.updatePaymentsByCheckNumber(checkNumber,paymentsDto),HttpStatus.OK);
	}
}
