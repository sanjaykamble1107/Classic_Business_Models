package com.sprint.classicmodelsbussiness.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.classicmodelsbussiness.dto.CustomersDto;
import com.sprint.classicmodelsbussiness.dto.PaymentsDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;
import com.sprint.classicmodelsbussiness.entity.Customers;
import com.sprint.classicmodelsbussiness.entity.Payments;
import com.sprint.classicmodelsbussiness.exception.CustomersNotFoundException;
import com.sprint.classicmodelsbussiness.exception.PaymentsNotFoundException;
import com.sprint.classicmodelsbussiness.repository.CustomersRepository;
import com.sprint.classicmodelsbussiness.repository.PaymentsRepository;
import com.sprint.classicmodelsbussiness.service.PaymentsService;

@Service
public class PaymentsServiceImpl implements PaymentsService {

	@Autowired
	public PaymentsRepository repository;

	@Autowired
	public CustomersRepository customersRepository;

	ResponseDto responseDto = new ResponseDto();

	@Override
	public ResponseDto savePayments(PaymentsDto payment) {
		try {
			Payments payments = new Payments();
			BeanUtils.copyProperties(payment, payments);
			payment.getCustomerNumber();
			Customers customers = new Customers();
			customers.setCustomerNumber(payment.getCustomerNumber());
			payments.setCustomers(customers);
			repository.save(payments);
			responseDto.setMessage("Payment details added Successfully");
			return responseDto;
		} catch (Exception e) {
			throw new CustomersNotFoundException("Customer Details not found ");
		}

	}

	@Override
	public List<PaymentsDto> getAllPaymentDetailsByPaymentDate(LocalDate paymentDate) {
		List<Payments> byPaymentDate = repository.findByPaymentDate(paymentDate);
		List<PaymentsDto> dtos = new ArrayList<>();
		for (Payments pay : byPaymentDate) {
			PaymentsDto payDto = new PaymentsDto();
			BeanUtils.copyProperties(pay, payDto);
			payDto.setCustomerNumber(pay.getCustomers().getCustomerNumber());
			dtos.add(payDto);
		}
		return dtos;
	}

	@Override
	public List<PaymentsDto> getAllPaymentDetailsByCustomerNumber(Integer customerNumber) {
		try {
			List<Payments> byCustomerNumber = repository.findByCustomersCustomerNumber(customerNumber);
			if (byCustomerNumber.isEmpty()) {
				throw new Exception();
			}
			List<PaymentsDto> dtos = new ArrayList<>();
			for (Payments pay : byCustomerNumber) {
				PaymentsDto payDto = new PaymentsDto();
				BeanUtils.copyProperties(pay, payDto);
				payDto.setCustomerNumber(pay.getCustomers().getCustomerNumber());
				dtos.add(payDto);
			}
			return dtos;
		} catch (Exception e) {
			throw new PaymentsNotFoundException("Customer Details not found " + customerNumber);
		}
	}

	@Override
	public PaymentsDto getAllPaymentDetailsByCheckNumber(String checkNumber) {

		Optional<Payments> findByCheckNumber = repository.findByCheckNumber(checkNumber);
		PaymentsDto dto = new PaymentsDto();
		BeanUtils.copyProperties(findByCheckNumber.get(), dto);
		dto.setCustomerNumber(findByCheckNumber.get().getCustomers().getCustomerNumber());
		return dto;

	}

	@Override
	public CustomersDto getCustomersByCheckNumber(String checkNumber) {
		Optional<Payments> findByCheckNumber = repository.findByCheckNumber(checkNumber);
		Optional<Customers> findById = customersRepository
				.findById(findByCheckNumber.get().getCustomers().getCustomerNumber());

		CustomersDto customersDto = new CustomersDto();
		BeanUtils.copyProperties(findById.get(), customersDto);
		return customersDto;

	}

	@Override
	public BigDecimal getTotalAmountOfPaymentMadeBySpecificCustomer(Integer customerNumber) {
		try {

			List<Payments> payList = repository.findByCustomersCustomerNumber(customerNumber);

			if (payList.isEmpty()) {
				throw new Exception();
			}

			BigDecimal amt = new BigDecimal("0");
			BigDecimal sum = new BigDecimal("0");
			;
			for (Payments pay : payList) {

				sum = sum.add(amt.add(pay.getAmount()));
			}
			return sum;
		} catch (Exception e) {
			throw new CustomersNotFoundException("Customer Details not found " + customerNumber);
		}

	}

	@Override
	public ResponseDto updateAmountOfSpecificCheckOfCustomer(Integer customerNumber, String checkNumber,
			BigDecimal newAmount) {
		try {
			Optional<Payments> paymentOptional = repository.findByCustomersCustomerNumberAndCheckNumber(customerNumber,
					checkNumber);
			if (paymentOptional.isEmpty()) {
				throw new Exception();
			}

			Payments payment = paymentOptional.get();
			payment.setAmount(newAmount);
			repository.save(payment);
			responseDto.setMessage("Payment amount details updated Successfully");
			return responseDto;

		} catch (Exception e) {
			throw new CustomersNotFoundException("Customer Details not found");
		}
	}

	@Override
	public List<CustomersDto> getAllCustomersWhoPaidOnGivenDate(LocalDate paymentDate) {
		try {
			List<Payments> paymentsList = repository.findByPaymentDate(paymentDate);
			if (paymentsList.isEmpty()) {
				throw new PaymentsNotFoundException("No payments found on the given date");
			}

			List<CustomersDto> customersDtoList = new ArrayList<>();
			for (Payments payment : paymentsList) {
				CustomersDto customersDto = new CustomersDto();
				BeanUtils.copyProperties(payment.getCustomers(), customersDto);
				customersDtoList.add(customersDto);
			}

			return customersDtoList;
		} catch (Exception e) {
			throw new PaymentsNotFoundException("Error retrieving payment details");
		}
	}

	@Override
	public List<CustomersDto> getCustomersDonePaymentInGivenDateRange(LocalDate startPayDate, LocalDate endPayDate) {

		List<Payments> paymentsList = repository.findByPaymentDateBetween(startPayDate, endPayDate);
		List<CustomersDto> customersDtoList = new ArrayList<>();
		for (Payments payment : paymentsList) {
			CustomersDto customersDto = new CustomersDto();
			BeanUtils.copyProperties(payment.getCustomers(), customersDto);
			customersDtoList.add(customersDto);
		}

		return customersDtoList;
	}

	@Override
	public List<CustomersDto> getCustomersByMaxPaymentAmount() {
		return null;
	}

	@Override
	public List<PaymentsDto> getAllPaymentsDetails() {
		List<Payments> list = repository.findAll();

		List<PaymentsDto> paymentsDtos = new ArrayList<>();
		for (Payments pay : list) {
			PaymentsDto dto = new PaymentsDto();
			
			dto.setCustomerNumber(pay.getCustomers().getCustomerNumber());
			BeanUtils.copyProperties(pay, dto);
			paymentsDtos.add(dto);
		}
		return paymentsDtos;
	}

	@Override
	public Boolean existsByCheckNumber(String checkNumber) {
		return repository.existsByCheckNumber(checkNumber);
	}

	@Override
	public ResponseDto updatePaymentById(String checkNumber, PaymentsDto dto) {
		Optional<Payments> paymentOpt = repository.findById(checkNumber);

		if (!paymentOpt.isPresent()) {
			throw new PaymentsNotFoundException("Payment not found.");
		}
		Payments payment = paymentOpt.get();
		BeanUtils.copyProperties(dto, payment);

//		    payment.setCustomers(customersRepository.findById( dto.getCustomerNumber()).get());
		payment.setAmount(dto.getAmount());

		payment.setPaymentDate(dto.getPaymentDate());

		repository.save(payment);

		responseDto.setMessage("Payment details updated successfully");
		return responseDto;
	}
}
