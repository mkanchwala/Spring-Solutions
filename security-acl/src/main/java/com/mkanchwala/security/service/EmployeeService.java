package com.mkanchwala.security.service;

import java.util.List;

import com.mkanchwala.security.model.Employee;

public interface EmployeeService {
	
	List<Employee> findAll();
	
	Employee saveOrUpdate(Employee t);
	
	void delete(Employee t);
	
	Employee findByEmployeeName(String nome);
	
	void deleteAll();
	
	List<Employee> filterEmployee(List<Employee> menus);
	
}
