package com.sprint.classicmodelsbussiness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.classicmodelsbussiness.entity.Offices;

public interface OfficeRepository extends JpaRepository<Offices, Integer> {
	
	public Offices findByCity(String city);

	public List<Offices> findAllByCity(String city);

	public List<Offices> getByCity(String city);

	public Boolean existsByPhone(String phone);

}
