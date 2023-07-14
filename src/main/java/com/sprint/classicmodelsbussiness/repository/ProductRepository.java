package com.sprint.classicmodelsbussiness.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint.classicmodelsbussiness.entity.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, String> {

	public Optional<Products> findByProductName(String productName);

	public List<Products> findByProductScale(String productScale);

	public List<Products> findByProductVendor(String productVendor);

	public Boolean existsByProductCode(String productCode);
}
