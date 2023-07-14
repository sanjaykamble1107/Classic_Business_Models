package com.sprint.classicmodelsbussiness.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint.classicmodelsbussiness.entity.Customers;
import com.sprint.classicmodelsbussiness.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {

	Optional<Orders> findByOrderNumber(Integer orderNumber);

	List<Orders> findByOrderDate(LocalDate orderDate);

	List<Orders> findByCustomer(Optional<Customers> cust);

	List<Orders> findByStatus(String status);

	List<Orders> findByRequiredDate(LocalDate date);

	List<Orders> findByShippedDate(LocalDate date);

	Boolean existsByOrderNumber(Integer orderNumber);

}
