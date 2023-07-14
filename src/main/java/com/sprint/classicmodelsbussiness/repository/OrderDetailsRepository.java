package com.sprint.classicmodelsbussiness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sprint.classicmodelsbussiness.entity.OrderDetails;
import com.sprint.classicmodelsbussiness.entity.OrderDetailsId;
import com.sprint.classicmodelsbussiness.entity.Orders;
import com.sprint.classicmodelsbussiness.entity.Products;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, OrderDetailsId> {

	List<OrderDetails> findByProduct(Products product);

	List<OrderDetails> findByOrder(Orders order);

	@Query("SELECT e FROM OrderDetails e WHERE (e.quantityOrdered * e.priceEach) = "
			+ "(SELECT MAX(o.quantityOrdered * o.priceEach) FROM OrderDetails o)")
	List<OrderDetails> getMaxOrderDetails();

	boolean existsByProductProductCode(String productLine);

	boolean existsByOrderOrderNumber(Integer orderNumber);

}
