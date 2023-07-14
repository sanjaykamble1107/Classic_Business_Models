package com.sprint.classicmodelsbussiness.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sprint.classicmodelsbussiness.entity.ProductLines;

@Repository
public interface ProductLinesRepository extends JpaRepository<ProductLines, String> {
	
	public Optional<ProductLines> findByTextDescription(String textDescription);

	public Boolean existsByProductLine(String productLine);
}
