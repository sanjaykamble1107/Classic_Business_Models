package com.sprint.classicmodelsbussiness.service;

import java.util.List;

import com.sprint.classicmodelsbussiness.dto.CustomersDto;
import com.sprint.classicmodelsbussiness.dto.OfficeDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;

public interface OfficeService {

	public ResponseDto saveOffice(OfficeDto officeDto);

	public List<OfficeDto> getAllOffices();

	public OfficeDto getOfficeByCode(Integer officeCode);

	public ResponseDto updateOfficePhone(Integer officeCode, String phoneNumber);

	public ResponseDto updateOfficeAddress(OfficeDto officeDto, Integer office_code);

//	public List<OfficeDto> getOfficeByCity(String city);
	public List<OfficeDto> getOfficeByCityList(String[] cities);

	public List<CustomersDto> getCustomersByOfficeCode(Integer officeCode);

	Boolean existsByPhone(String phone);
}
