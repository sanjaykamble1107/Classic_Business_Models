package com.sprint.classicmodelsbussiness.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.sprint.classicmodelsbussiness.dto.CustomersDto;
import com.sprint.classicmodelsbussiness.dto.PaymentsDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;

public interface PaymentsService {

	public ResponseDto savePayments(PaymentsDto payment);

	public List<PaymentsDto> getAllPaymentDetailsByPaymentDate(LocalDate paymentDate);

	public List<PaymentsDto> getAllPaymentDetailsByCustomerNumber(Integer customerNumber);

	public PaymentsDto getAllPaymentDetailsByCheckNumber(String checkNumber);

	public CustomersDto getCustomersByCheckNumber(String checkNumber);

	public BigDecimal getTotalAmountOfPaymentMadeBySpecificCustomer(Integer customerNumber);

	public ResponseDto updateAmountOfSpecificCheckOfCustomer(Integer customerNumber, String checkNumber,
			BigDecimal newAmount);

	List<CustomersDto> getCustomersDonePaymentInGivenDateRange(LocalDate startPayDate, LocalDate endPayDate);

	public List<CustomersDto> getAllCustomersWhoPaidOnGivenDate(LocalDate paymentDate);

	List<CustomersDto> getCustomersByMaxPaymentAmount();

	public List<PaymentsDto> getAllPaymentsDetails();

	Boolean existsByCheckNumber(String checkNumber);

	ResponseDto updatePaymentById(String checkNumber, PaymentsDto dto);

}
