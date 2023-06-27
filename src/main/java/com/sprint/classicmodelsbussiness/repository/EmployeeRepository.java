package com.sprint.classicmodelsbussiness.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.classicmodelsbussiness.entity.Employees;

public interface EmployeeRepository  extends JpaRepository<Employees,Integer>{

}
