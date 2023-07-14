package com.sprint.classicmodelsbussiness.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.classicmodelsbussiness.entity.Customers;
import com.sprint.classicmodelsbussiness.entity.Payments;

public interface PaymentsRepository extends JpaRepository<Payments, String> {

	public List<Payments> findByPaymentDate(LocalDate paymentDate);

	public List<Payments> findByCustomersCustomerNumber(Integer customerNumber);

	public Optional<Payments> findByCheckNumber(String checkNumber);

	public void save(Customers customer);

	public List<Payments> findByPaymentDateBetween(LocalDate startPayDate, LocalDate endPayDate);

	public Optional<Payments> findByCustomersCustomerNumberAndCheckNumber(Integer customerNumber, String checkNumber);

	public Boolean existsByCheckNumber(String checkNumber);

}
