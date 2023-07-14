package com.sprint.classicmodelsbussiness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.classicmodelsbussiness.entity.Employees;
import com.sprint.classicmodelsbussiness.entity.Offices;

public interface EmployeeRepository extends JpaRepository<Employees, Integer> {

	// List<Employees> findEmployeeByOfficeCode(Integer officeCode);

	List<Employees> findByOfficeOfficeCode(Integer officeCode);

	List<Employees> findByOffice(Offices office);

	boolean existsByEmail(String email);

}
