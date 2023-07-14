package com.sprint.classicmodelsbussiness.service;

import java.math.BigDecimal;
import java.util.List;

import com.sprint.classicmodelsbussiness.dto.CustomersDto;
import com.sprint.classicmodelsbussiness.dto.OrderProductsStaffDto;
import com.sprint.classicmodelsbussiness.dto.OrdersPaymentsDto;
import com.sprint.classicmodelsbussiness.dto.PaymentsDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;

public interface CustomersService {

	public ResponseDto saveCustomer(CustomersDto customersDto);

	public CustomersDto getCustomerById(Integer customerNumber);

	public List<CustomersDto> getCustomerByCustomerName(String customerName);

	public List<CustomersDto> getCustomersByCity(String city);

	public List<CustomersDto> getCustomersByCountry(String country);

	public List<CustomersDto> getCustomerByPhone(String phone);

	public List<CustomersDto> getCustomerBycontactFirstName(String contactFirstName);

	public List<CustomersDto> getCustomerBycontactLastName(String contactLastName);

	public List<CustomersDto> getCustomersBySalesRepEmployeeNumber(Integer salesRepEmployeeNumber);

	public CustomersDto updateNameOfCustomerByCustomerNumber(Integer customerNumber, String customerName);

	public List<CustomersDto> getCustomersByCreditLimit(BigDecimal creditLimit);

	public List<CustomersDto> getCustomersByPostalCode(String postalCode);

	public CustomersDto updateContactLastNameByCustomerNumber(Integer customerNumber, String contactLastName);

	public CustomersDto updateContactFirstNameByCustomerNumber(Integer customerNumber, String contactFirstName);

	public ResponseDto updateAddressByCustomerNumber(Integer customerNumber, CustomersDto customersDto);

	public List<CustomersDto> getAllCustomers();

	public List<CustomersDto> getCustomersBetweenGivenRangeOfCredit(BigDecimal start, BigDecimal end);

	public List<CustomersDto> getCustomersWhereCreditLimitIsGreaterThan(BigDecimal creditlimit);

	public List<CustomersDto> getCustomersWhereCreditlimitIsLowerThan(BigDecimal creditlimit);

	public List<PaymentsDto> getPaymentDetailsForParticularCustomer(Integer customerNumber);

	public List<CustomersDto> getCustomersByFirstnameLike(String fn);

	List<OrderProductsStaffDto> getOrderProductStaffByCustomer(Integer customerNumber);

	List<OrdersPaymentsDto> getOrderPaymentsByCustomer(String customerName);

	public List<CustomersDto> getCustomersOnPage(Integer pageSize, Integer pageNo, String sortBy, String sortDir);

	Boolean existsByCustomerNumber(Integer customerNumber);

	ResponseDto updateCustomerById(Integer customerNumber, CustomersDto dto);

//public CustomersDto getAllOrdersWithProductInfoAndAllStafByCustId(Integer customerNumber);

//public CustomersDto getPaymentDetailsForParticularCustomer(Integer customerNumber);

//public CustomersDto showOrderDetailsWithPaymentDetailsForContactFirstName(String contactFirstName);

}