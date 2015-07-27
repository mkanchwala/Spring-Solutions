package com.mkanchwala.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mkanchwala.security.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	public Employee findByName(String name);

}
