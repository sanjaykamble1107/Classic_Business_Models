package com.sprint.classicmodelsbussiness.repository;

import java.math.BigDecimal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.sprint.classicmodelsbussiness.entity.Customers;

public interface CustomersRepository
		extends JpaRepository<Customers, Integer>, PagingAndSortingRepository<Customers, Integer> {

	public List<Customers> findByCustomerName(String name);

	public List<Customers> findByCity(String city);

	public List<Customers> findByCountry(String country);

	public List<Customers> findByPhone(String phone);

	public List<Customers> findByContactFirstName(String contactFirstName);

	public List<Customers> findByContactLastName(String contactLastName);

	public List<Customers> findByCreditLimit(BigDecimal creditlimit);

	public List<Customers> findByPostalCode(String postalCode);

	public List<Customers> findBySalesRepEmployeeNumber(Integer salesRepEmployeeNumber);

	public List<Customers> findByCreditLimitBetween(BigDecimal start, BigDecimal end);

	public List<Customers> findByCreditLimitLessThan(BigDecimal creditlimit);

	public List<Customers> findByCreditLimitGreaterThan(BigDecimal creditlimit);

	public List<Customers> findByCustomerNumber(Integer customerNumber);

	@Query(value = "SELECT * FROM customers WHERE contactFirstName LIKE :fn%", nativeQuery = true)
	public List<Customers> findByContactFirstNameLike(String fn);

	public Boolean existsByCustomerNumber(Integer customerNumber);

	public List<Customers> findBySalesRepEmployeeNumberEmployeeNumber(Integer salesRepEmployeeNumber);
}
