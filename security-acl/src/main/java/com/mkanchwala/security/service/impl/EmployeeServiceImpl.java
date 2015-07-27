package com.mkanchwala.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mkanchwala.security.model.Employee;
import com.mkanchwala.security.repository.EmployeeRepository;
import com.mkanchwala.security.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired private EmployeeRepository menuRepository;

	@Override
	public List<Employee> findAll() {
		return menuRepository.findAll();
	}

	@Override
	public Employee saveOrUpdate(Employee t) {
		return menuRepository.save(t);
	}

	@Override
	public void delete(Employee t) {
		menuRepository.delete(t);
	}

	@Override
	public Employee findByEmployeeName(String nome) {
		return menuRepository.findByName(nome);
	}

	@Override
	public void deleteAll() {
		menuRepository.deleteAll();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PreFilter("hasPermission(filterObject, 'administration')")
	public List<Employee> filterEmployee(List<Employee> menus) {
		return menus;
	}
}
