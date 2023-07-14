package com.sprint.classicmodelsbussiness.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.classicmodelsbussiness.dto.CustomersDto;
import com.sprint.classicmodelsbussiness.dto.OfficeDto;
import com.sprint.classicmodelsbussiness.dto.ResponseDto;
import com.sprint.classicmodelsbussiness.entity.Employees;
import com.sprint.classicmodelsbussiness.entity.Offices;
import com.sprint.classicmodelsbussiness.exception.OfficeNotFoundException;
import com.sprint.classicmodelsbussiness.repository.CustomersRepository;
import com.sprint.classicmodelsbussiness.repository.EmployeeRepository;
import com.sprint.classicmodelsbussiness.repository.OfficeRepository;
import com.sprint.classicmodelsbussiness.service.OfficeService;

@Service
public class OfficeServiceImpl implements OfficeService {

	@Autowired
	private OfficeRepository repository;

	@Autowired
	private EmployeeRepository empRepository;

	@Autowired
	private CustomersRepository cusRepository;

	private ResponseDto responseDto = new ResponseDto();

	/**
	 * Saves an office's details.
	 *
	 * @param officeDto The DTO containing the office details to be saved.
	 * @return A ResponseDto indicating the success or failure of the operation.
	 * @throws OfficeNotFoundException If the office's phone number already exists.
	 */
	@Override
	public ResponseDto saveOffice(OfficeDto officeDto) throws OfficeNotFoundException {
		if (existsByPhone(officeDto.getPhone())) {
			throw new OfficeNotFoundException("Phone number already exists");
		}
		try {
			Offices ofc = new Offices();
			BeanUtils.copyProperties(officeDto, ofc);
			repository.save(ofc);
			officeDto.setOfficeCode(ofc.getOfficeCode());

			responseDto.setMessage("Office details added successfully");
			return responseDto;
		} catch (Exception e) {
			throw new OfficeNotFoundException("Failed to add office details");
		}
	}

	/**
	 * Retrieves a list of all offices.
	 *
	 * @return A list of OfficeDto objects representing the offices.
	 */
	@Override
	public List<OfficeDto> getAllOffices() {
		// Retrieve all offices from the repository
		List<Offices> findAll = repository.findAll();
		List<OfficeDto> dtos = new ArrayList<>();
		for (Offices ofc : findAll) {
			OfficeDto dto = new OfficeDto();
			BeanUtils.copyProperties(ofc, dto);
			dtos.add(dto);
		}
		return dtos;
	}

	/**
	 * Retrieves an office by its office code.
	 *
	 * @param officeCode The code of the office to retrieve.
	 * @return An OfficeDto object representing the office.
	 * @throws OfficeNotFoundException If the office with the given code is not
	 *                                 found.
	 */
	@Override
	public OfficeDto getOfficeByCode(Integer officeCode) throws OfficeNotFoundException {
		// Find an office by its code in the repository
		Optional<Offices> findById = repository.findById(officeCode);

		if (findById.isPresent()) {
			OfficeDto dto = new OfficeDto();
			BeanUtils.copyProperties(findById.get(), dto);
			return dto;
		}
		throw new OfficeNotFoundException("Office details not found");
	}

	/**
	 * Updates the phone number of an office.
	 *
	 * @param officeCode  The code of the office to update.
	 * @param phoneNumber The new phone number.
	 * @return A ResponseDto indicating the success or failure of the operation.
	 * @throws OfficeNotFoundException If the office with the given code is not
	 *                                 found.
	 */
	@Override
	public ResponseDto updateOfficePhone(Integer officeCode, String phoneNumber) throws OfficeNotFoundException {
		// Find an office by its code in the repository
		Optional<Offices> findById = repository.findById(officeCode);

		if (findById.isPresent()) {
			Offices ofc = new Offices();
			// Update the phone number of the office
			findById.get().setPhone(phoneNumber);
			BeanUtils.copyProperties(findById.get(), ofc);
			repository.save(ofc);
			OfficeDto ofcDto = new OfficeDto();
			BeanUtils.copyProperties(ofc, ofcDto);
			responseDto.setMessage("Office phone number updated successfully");
			return responseDto;
		}
		throw new OfficeNotFoundException("Office details not found");
	}

	/**
	 * Updates the address of an office.
	 *
	 * @param officeDto  The DTO containing the updated office details.
	 * @param officeCode The code of the office to update.
	 * @return A ResponseDto indicating the success or failure of the operation.
	 * @throws OfficeNotFoundException If the office with the given code is not
	 *                                 found.
	 */
	@Override
	public ResponseDto updateOfficeAddress(OfficeDto officeDto, Integer officeCode) throws OfficeNotFoundException {
		// Find an office by its code in the repository
		Optional<Offices> findById = repository.findById(officeCode);

		if (findById.isPresent()) {
			Offices ofc = findById.get();
			// Update the address of the office
			BeanUtils.copyProperties(officeDto, ofc);
			ofc.setOfficeCode(officeCode);
			repository.save(ofc);
			responseDto.setMessage("Office address updated successfully");
			return responseDto;
		}
		throw new OfficeNotFoundException("Office details not found");
	}

	/**
	 * Retrieves a list of offices based on a list of cities.
	 *
	 * @param cities An array of cities to filter the offices by.
	 * @return A list of OfficeDto objects representing the filtered offices.
	 */
	@Override
	public List<OfficeDto> getOfficeByCityList(String[] cities) {
		List<OfficeDto> dtos = new ArrayList<>();
		for (String city : cities) {
			// Retrieve offices by city from the repository
			List<Offices> findAllbyCity = repository.getByCity(city);
			for (Offices ofc : findAllbyCity) {
				OfficeDto dto = new OfficeDto();
				BeanUtils.copyProperties(ofc, dto);
				dtos.add(dto);
			}
		}
		return dtos;
	}

	/**
	 * Retrieves a list of customers associated with an office.
	 *
	 * @param officeCode The code of the office.
	 * @return A list of CustomersDto objects representing the customers.
	 */
	@Override
	public List<CustomersDto> getCustomersByOfficeCode(Integer officeCode) {
//         Find employees associated with the office
		List<Employees> empList = empRepository.findByOfficeOfficeCode(officeCode);
		CustomersServiceImpl customersServiceImpl = new CustomersServiceImpl(cusRepository);
		List<CustomersDto> list = new ArrayList<>();
		for (Employees emp : empList) {
			try {
				// Get customers by sales representative employee number
				List<CustomersDto> custList = customersServiceImpl
						.getCustomersBySalesRepEmployeeNumber(emp.getEmployeeNumber());
				for (CustomersDto customersDto : custList) {
					list.add(customersDto);
				}
			} catch (Exception e) {
				continue;
			}
		}
		return list;
	}

	/**
	 * Checks if an office with the given phone number already exists.
	 *
	 * @param phone The phone number to check.
	 * @return true if the office with the given phone number exists, false
	 *         otherwise.
	 */
	@Override
	public Boolean existsByPhone(String phone) {
		return repository.existsByPhone(phone);
	}

}
